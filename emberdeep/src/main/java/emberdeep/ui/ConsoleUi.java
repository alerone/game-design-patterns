package emberdeep.ui;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.item.Inventory;
import emberdeep.engine.item.Item;
import emberdeep.engine.session.GameSession;
import emberdeep.engine.world.map.GameMap;
import emberdeep.engine.world.map.TileType;
import emberdeep.engine.world.map.TileTypes;
import lombok.RequiredArgsConstructor;

/**
 * ConsoleUi
 */
@RequiredArgsConstructor
public class ConsoleUi {

    private final Scanner in;
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
            for (Fighter hero : session.getHeroes()) {
                if (!hero.isAlive() || combat.isOver())
                    continue;
                out.println("Héroes: " + describe(session.getHeroes()));
                out.println("Enemigos: " + describe(session.getEnemies()));
                out.println(describeItems(session.getInventory()));
                out.println("Turno de " + hero.getName() + ". [1] atacar");
                ask(1);
                Fighter target = askTarget(session.getEnemies());
                print(combat.heroAttack(hero, target));
            }

            if (!combat.isOver()) {
                print(combat.enemyPhase());
            }
        }
        out.println(combat.heroesWon() ? "Victoria" : "Derrota...");
    }

    public boolean explore(GameMap map, TileTypes types) {
        while (true) {
            out.println(map.render());
            out.println("[w a s d] mover; [t] casillas; [q] salir");
            out.print("> ");
            out.flush();
            String key = in.nextLine().trim();
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
        messages.forEach(out::println);
    }

    private int ask(int max) {
        while (true) {
            out.println("> ");
            out.flush();
            String line = in.nextLine().trim();

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
