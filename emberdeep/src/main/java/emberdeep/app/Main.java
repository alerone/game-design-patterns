package emberdeep.app;

import java.util.List;
import java.util.Scanner;

import emberdeep.engine.character.Enemy;
import emberdeep.engine.character.Hero;
import emberdeep.engine.character.Stats;
import emberdeep.engine.session.GameSession;
import emberdeep.ui.ConsoleUi;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        List<Hero> heroes = List.of(
                new Hero("Aria", new Stats(30,8,3)),
                new Hero("Borin", new Stats(40,5,5)),
                new Hero("Cael", new Stats(24,9,2)));
        List<Enemy> enemies = List.of(
                new Enemy("Goblin", new Stats(14,6,1)),
                new Enemy("Rata de ceniza", new Stats(8,5,0)),
                new Enemy("Esqueleto", new Stats(18,7,2)));
        GameSession session = new GameSession(heroes, enemies);
        ConsoleUi ui = new ConsoleUi(new Scanner(System.in), System.out);
        ui.run(session);
    }
}
