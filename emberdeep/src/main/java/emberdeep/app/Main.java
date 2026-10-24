package emberdeep.app;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import emberdeep.engine.character.Enemy;
import emberdeep.engine.character.Hero;
import emberdeep.engine.character.Stats;
import emberdeep.engine.combat.ai.FirstAliveAi;
import emberdeep.engine.combat.ai.LowestHpAi;
import emberdeep.engine.combat.ai.RandomTargetAi;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.combat.damage.FlatFormula;
import emberdeep.engine.combat.damage.ScaledFormula;
import emberdeep.engine.session.GameSession;
import emberdeep.ui.ConsoleUi;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Random random = new Random(42);
        ConsoleUi ui = new ConsoleUi(new Scanner(System.in), System.out);
        DamageFormula formula = ui.askFormula() == 1 
            ? new FlatFormula()
            : new ScaledFormula();

        List<Hero> heroes = List.of(
                new Hero("Aria", new Stats(30,8,3)),
                new Hero("Borin", new Stats(40,5,5)),
                new Hero("Cael", new Stats(24,9,2)));
        List<Enemy> enemies = List.of(
                new Enemy("Goblin", new Stats(14,6,1), new FirstAliveAi()),
                new Enemy("Rata de ceniza", new Stats(8,5,0), new LowestHpAi()),
                new Enemy("Esqueleto", new Stats(18,7,2), new RandomTargetAi(random)));
        GameSession session = new GameSession(heroes, enemies, formula);
        ui.run(session);
    }
}
