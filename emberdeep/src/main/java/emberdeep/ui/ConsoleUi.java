package emberdeep.ui;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
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
import emberdeep.engine.session.GameSession;
import emberdeep.engine.world.map.GameMap;
import emberdeep.engine.world.map.TileType;
import emberdeep.engine.world.map.TileTypes;
import emberdeep.ui.input.InputSource;
import lombok.RequiredArgsConstructor;

/**
 * ConsoleUi
 */
@RequiredArgsConstructor
public class ConsoleUi {

    private final InputSource in;
    private final PrintStream out;

    public int askFormula() {
        out.println("Fórmula de daño:");
        out.println(" [1] plana: ataque - defensa");
        out.println(" [2] escalada: ataque x 100 / (100 + defensa)");
        return ask(2);
    }

    public void run(GameSession session) {
        Combat combat = session.getCombat();
        out.println("=== Emberdeep: planta 1 ===");
        while (!combat.isOver()) {
            out.println("--- Ronda " + session.round() + " ---");
            List<Fighter> acting = session.getHeroes().stream()
                .filter(Fighter::isAlive).toList();
            int i = 0;
            while(i < acting.size() && !combat.isOver()) {
                boolean acted = heroTurn(session, acting.get(i));
                i += acted ? 1 : -1;
            }

            if (!combat.isOver()) 
                print(combat.enemyPhase());
        }
        out.println(combat.heroesWon() ? "Victoria" : "Derrota...");
    }

    private boolean heroTurn(GameSession session, Fighter hero) {
        Combat combat = session.getCombat();
        while (true) {
            out.println("Héroes:    " + describe(session.getHeroes()));
            out.println("Enemigos:  " + describe(session.getEnemies()));
            out.println("Turno de:  " + hero.getName()
                        + ". [1] atacar  [2] defender  [3] objeto" 
                        + "  [4] habilidad  [u] deshacer");
            String key = in.next();
            if (key.equals("u")) {
                if (combat.undo()) {
                    out.println("Deshaces la última acción.");
                    return false;
                }
                out.println("No hay nada que deshacer en esta ronda.");
            }
            CombatCommand command = switch(key) {
                case "1" -> new Attack(combat, hero, askTarget("Objetivo", session.getEnemies()));
                case "2" -> new Defend(hero);
                case "3" -> chooseItem(session, hero);
                case "4" -> chooseSkill(session, hero);
                default -> {
                    out.println("Tecla no válida");
                    yield null;
                }
            };
            if (command != null) {
                print(combat.perform(command));
                return true;
            }
        }
    }

    private CombatCommand chooseItem(GameSession session, Fighter hero) {
        List<Item> items = session.getInventory().items();
        if (items.isEmpty()) {
            out.println("No te quedan objetos.");
            return null;
        }

        Item item = items.get(choose("Objeto", items.stream().map(Item::name).toList()));
        Fighter target = item.kind() == ItemKind.HEALING
            ? askTarget("Aliado", session.getHeroes())
            : askTarget("Objetivo", session.getEnemies());
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
        Fighter target = askTarget("Objetivo", session.getEnemies());
        return new UseSkill(session.getCombat(), hero, skill, target);
    }

    private Fighter askTarget(String title, List<Fighter> group) {
        List<Fighter> alive = group.stream().filter(Fighter::isAlive).toList();
        return alive.get(choose(title, alive.stream().map(Fighter::getName).toList()));
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

    private String describeItems(Inventory inventory) {
        if (inventory.items().isEmpty())
            return "(ninguno)";
        return "Objetos: " + inventory.items().stream().map(Item::name).collect(Collectors.joining(", "));
    }

    private String describe(List<Fighter> group) {
        return group.stream()
                .map(f -> f.getName() + " " + f.getHp() + "/" + f.getStats().maxHp())
                .collect(Collectors.joining(", "));
    }

    private void print(List<String> messages) {
        messages.forEach(s -> out.println("\u001B[36m" + s + "\u001B[0m"));
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

    private Fighter askTarget(List<Fighter> enemies) {
        List<Fighter> alive = enemies.stream().filter(Fighter::isAlive).toList();
        StringBuilder menu = new StringBuilder("Objetivo: ");
        for (int i = 0; i < alive.size(); i++) {
            menu.append(" [").append(i + 1).append("] ").append(alive.get(i).getName());
        }
        out.println(menu);
        return alive.get(ask(alive.size()) - 1);
    }
}
