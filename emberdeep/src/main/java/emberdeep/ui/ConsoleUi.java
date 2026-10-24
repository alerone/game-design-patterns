package emberdeep.ui;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.session.GameSession;
import lombok.RequiredArgsConstructor;

/**
 * ConsoleUi
 */
@RequiredArgsConstructor 
public class ConsoleUi {

    private final Scanner in;
    private final PrintStream out;

    public void run(GameSession session) {
        Combat combat = session.getCombat();
        out.println("=== Emberdeep: planta 1 ===");
        while(!combat.isOver()) {
            out.println("--- Ronda " + session.round() + " ---");
            for (Fighter hero : session.getHeroes()) {
                if (!hero.isAlive() || combat.isOver()) continue;
                out.println("Héroes: " + describe(session.getHeroes()));
                out.println("Enemigos: " + describe(session.getEnemies()));
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
                if (n <= 1 && n <= max) {
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
