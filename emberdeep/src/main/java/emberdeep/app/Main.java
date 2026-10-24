package emberdeep.app;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Hero;
import emberdeep.engine.character.Stats;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.combat.damage.FlatFormula;
import emberdeep.engine.combat.damage.ScaledFormula;
import emberdeep.engine.session.GameSession;
import emberdeep.engine.world.biome.BiomeFactory;
import emberdeep.engine.world.biome.CavernBiome;
import emberdeep.engine.world.biome.CryptBiome;
import emberdeep.ui.ConsoleUi;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        ConsoleUi ui = new ConsoleUi(new Scanner(System.in), System.out);
        DamageFormula formula = ui.askFormula() == 1
                ? new FlatFormula()
                : new ScaledFormula();
        Random random = new Random(42);

        boolean cavern = args.length > 0 && args[0].equals("2");
        BiomeFactory biome = cavern
                ? new CavernBiome(random)
                : new CryptBiome(random);

        List<EnemyKind> kinds = cavern
                ? List.of(EnemyKind.GOBLIN, EnemyKind.BAT, EnemyKind.SPIDER)
                : List.of(EnemyKind.SKELETON, EnemyKind.ASH_RAT, EnemyKind.ZOMBIE);

        List<Hero> heroes = List.of(
                new Hero("Aria", new Stats(30, 8, 3)),
                new Hero("Borin", new Stats(40, 6, 5)),
                new Hero("Cael", new Stats(24, 9, 2)));
        GameSession session = new GameSession(heroes, biome, kinds, formula);

        ui.run(session);
    }

}
