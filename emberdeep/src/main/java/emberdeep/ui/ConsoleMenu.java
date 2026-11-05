package emberdeep.ui;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Skill;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.command.Attack;
import emberdeep.engine.combat.command.CombatCommand;
import emberdeep.engine.combat.command.Defend;
import emberdeep.engine.combat.command.UseItem;
import emberdeep.engine.combat.command.UseSkill;
import emberdeep.engine.item.Inventory;
import emberdeep.engine.item.Item;
import emberdeep.engine.item.ItemKind;
import emberdeep.engine.save.SnapshotStore;
import emberdeep.engine.session.GameSession;
import emberdeep.engine.world.map.GameMap;
import emberdeep.engine.world.map.TileType;
import emberdeep.engine.world.map.TileTypes;
import emberdeep.ui.input.InputSource;
import emberdeep.utils.TermColors;
import lombok.RequiredArgsConstructor;

/**
 * ConsoleUi
 */
@RequiredArgsConstructor
public class ConsoleMenu {
    private enum Turn { ACTED, UNDONE, RESTORED }

    private final InputSource in;
    private final PrintStream out;
    private final SnapshotStore checkpoints;
    private int checkpointHero;

    public int askFormula() {
        out.println("Fórmula de daño:");
        out.println(" [1] plana: ataque - defensa");
        out.println(" [2] escalada: ataque x 100 / (100 + defensa)");
        return ask(2);
    }

    public void run(GameSession session) {
        Combat combat = session.combat();
        out.println("=== Emberdeep: planta 1 ===");
        while (!combat.isOver()) {
            out.println("--- Ronda " + session.round() + " ---");
            List<Fighter> acting = aliveHeroes(session);
            int i = 0;
            while(i < acting.size() && !combat.isOver()) {
                Turn turn = heroTurn(session, acting.get(i), i);
                switch (turn) {
                    case ACTED -> i++;
                    case UNDONE -> i = Math.max(0, i - 1);
                    case RESTORED -> {
                        acting = aliveHeroes(session);
                        i = checkpointHero;
                    }
                }
            }
            if (!combat.isOver()) 
                combat.enemyPhase();
        }
    }

    private List<Fighter> aliveHeroes(GameSession session) {
        return session.heroes().stream().filter(Fighter::isAlive).toList();
    }

    private Turn heroTurn(GameSession session, Fighter hero, int index) {
        Combat combat = session.combat();
        while (true) {
            printBlue("Héroes:    " + describe(session.heroes()));
            printRed("Enemigos:  " + describe(session.enemies()));
            printGreen("Objetos: " + describeItems(session.inventory()));
            out.println("Turno de:  " + hero.name()
                        + ". [1] atacar  [2] defender  [3] objeto" 
                        + "  [4] habilidad  [u] deshacer [c] guardar [r] volver");
            String key = in.next();
            if (key.equals("u")) {
                if (combat.undo()) {
                    out.println("Deshaces la última acción.");
                    return Turn.UNDONE;
                }
                out.println("No hay nada que deshacer en esta ronda.");
                continue;
            }

            if (key.equals("c")) {
                saveCheckpoint(session, index);
                continue;
            }

            if(key.equals("r")) {
                if (loadCheckpoint(session)){
                    return Turn.RESTORED;
                }
                continue;
            }
            CombatCommand command = switch(key) {
                case "1" -> new Attack(combat, hero, askTarget("Objetivo", session.enemies()));
                case "2" -> new Defend(combat, hero);
                case "3" -> chooseItem(session, hero);
                case "4" -> chooseSkill(session, hero);
                default -> {
                    out.println("Tecla no válida");
                    yield null;
                }
            };
            if (command != null) {
                combat.perform(command);
                return Turn.ACTED;
            }
        }
    }

    private void saveCheckpoint(GameSession session, int index) {
        checkpoints.push(session.createSnapshot()); 
        checkpointHero = index;
        out.println("Punto de control guardado (" +
                checkpoints.size() + ").");
    }

    private boolean loadCheckpoint(GameSession session) {
        if (checkpoints.isEmpty()) {
            out.println("No hay puntos de control.");
            return false;
        }
        session.restore(checkpoints.last());
        out.println("Vuelves al último punto de control (ronda " + session.round() + ").");
        return true;
    }

    private CombatCommand chooseItem(GameSession session, Fighter hero) {
        List<Item> items = session.inventory().items();
        if (items.isEmpty()) {
            out.println("No te quedan objetos.");
            return null;
        }

        Item item = items.get(choose("Objeto", items.stream().map(Item::name).toList()));
        Fighter target = item.kind() == ItemKind.HEALING
            ? askTarget("Aliado", session.heroes())
            : askTarget("Objetivo", session.enemies());
        return new UseItem(session, hero, item, target);
    }

    private CombatCommand chooseSkill(GameSession session, Fighter hero) {
        List<Skill> skills = hero.skills();
        if (skills.isEmpty()) {
            out.println("No tienes habilidades.");
            return null;
        }
        Skill skill = skills.get(choose("Habilidad", 
                    skills.stream().map(Skill::name).toList()));
        Fighter target = askTarget("Objetivo", session.enemies());
        return new UseSkill(session.combat(), hero, skill, target);
    }

    private Fighter askTarget(String title, List<Fighter> group) {
        List<Fighter> alive = group.stream().filter(Fighter::isAlive).toList();
        return alive.get(choose(title, alive.stream().map(Fighter::name).toList()));
    }

    private int choose(String title, List<String> labels) {
        StringBuilder menu = new StringBuilder();
        for (int i = 0; i < labels.size(); i++) {
            menu.append("  [").append(i+1).append("]  ")
                .append(labels.get(i));
        }
        out.println(menu);
        return ask(labels.size()) - 1;
    }

    public boolean explore(GameMap map, TileTypes types) {
        while (true) {
            out.println(map.render());
            out.println("[w a s d] mover; [t] casillas; [q] salir");
            out.print("> ");
            out.flush();
            String key = in.next();
            switch (key) {
                case "w" -> step(map, 0, -1);
                case "a" -> step(map, -1, 0);
                case "s" -> step(map, 0, 1);
                case "d" -> step(map, 1, 0);
                case "t" -> showTiles(map, types);
                case "q" -> {
                    return false;
                }
                default -> out.println("Técla inválida.");
            }
            ;

            if (map.onStairs()) {
                out.println("Bajas por la escalera.");
                return true;
            }
        }
    }

    private void step(GameMap map, int dx, int dy) {
        if (!map.move(dx, dy))
            out.println("No puedes pasar.");
    }

    private void showTiles(GameMap map, TileTypes types) {
        TileType corner = map.tileAt(0, 0);
        TileType opposite = map.tileAt(map.width() - 1, map.height() - 1);

        out.println("Casillas: " + map.width() * map.height() + 
                ", TileType creados: " + types.createdCount());
        out.println("Esquinas opuestas, mismo objeto: " + (corner == opposite));
    }

    private String describe(List<Fighter> group) {
        return group.stream()
                .map(f -> f.name() + " " + f.hp() + "/" + f.stats().maxHp())
                .collect(Collectors.joining(", "));
    }
    
    private String describeItems(Inventory inventory) {
        if (inventory.items().isEmpty()) return "(ninguno)";
        return inventory.items().stream()
            .map(Item::name)
            .collect(Collectors.joining(", "));
    }

    private void printBlue(String s) {
        out.println(TermColors.toBlue(s));
    }

    private void printRed(String s) {
        out.println(TermColors.toRed(s));
    }

    private void printGreen(String s) {
        out.println(TermColors.toGreen(s));
    }

    private int ask(int max) {
        while (true) {
            out.println("> ");
            out.flush();
            String line = in.next();

            try {
                int n = Integer.parseInt(line);
                if (n >= 1 && n <= max) {
                    return n;
                }
            } catch (NumberFormatException e) {

            }
            out.println("Escribe un número entre 1 y " + max + ".");
        }
    }

}
