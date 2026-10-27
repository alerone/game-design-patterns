package emberdeep.app;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Hero;
import emberdeep.engine.character.HeroBuilder;
import emberdeep.engine.character.Stats;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.combat.damage.FlatFormula;
import emberdeep.engine.combat.damage.ScaledFormula;
import emberdeep.engine.session.GameSession;
import emberdeep.engine.world.Dungeon;
import emberdeep.engine.world.DungeonBuilder;
import emberdeep.engine.world.Floor;
import emberdeep.engine.world.biome.BiomeFactory;
import emberdeep.engine.world.biome.CavernBiome;
import emberdeep.engine.world.biome.CryptBiome;
import emberdeep.ui.ConsoleUi;

/**
 * Main
 */
public class Main {
    private final GameConfig config;

    private Main(GameConfig config) { this.config = config; }

    public static void main(String[] args) {
        GameConfig config = new GameConfig(42L, 3, new FlatFormula());
        new Main(config).play(args);
    }
    private void play(String[] args) {
        int floorNumber = chooseFloor(args);
        ConsoleUi ui = new ConsoleUi(new Scanner(System.in), System.out);
        DamageFormula formula = chooseFormula(ui, args);
        Random random = new Random(config.seed());

        BiomeFactory crypt = new CryptBiome(random);
        BiomeFactory cavern = new CavernBiome(random);

        List<EnemyKind> cryptEnemies = List.of(EnemyKind.SKELETON, EnemyKind.ASH_RAT, EnemyKind.ZOMBIE);
        List<EnemyKind> cavernEnemies = List.of(EnemyKind.GOBLIN, EnemyKind.BAT, EnemyKind.SPIDER);

        Dungeon dungeon = new DungeonBuilder()
            .floor().biome(crypt).enemies(cryptEnemies).done()
            .floor().biome(cavern).enemies(cavernEnemies).done()
            .floor().biome(crypt).enemies(cryptEnemies).done().build();
        Floor floor = dungeon.floors().get(floorNumber - 1);

        GameSession session = new GameSession(heroes(), floor.biome(), floor.enemies(), formula);

        ui.run(session);
    }


    private DamageFormula chooseFormula(ConsoleUi ui, String[] args) {
        if (args.length > 1 && args[1].equals("default"))
            return config.defaultFormula();
        return ui.askFormula() == 1 ? new FlatFormula() : new ScaledFormula();
    }

    private int chooseFloor(String[] args) {
        int floor = args.length > 0 ? Integer.parseInt(args[0]) : 1;
        if (floor < 1 || floor > config.floors()) {
            throw new IllegalArgumentException(
                    "La planta debe estar entre 1 y " + config.floors());
        }
        return floor;
    }

    private List<Hero> heroes() {
        return List.of(
                new HeroBuilder().name("Aria").stats(new Stats(30, 8, 3)).build(),
                new HeroBuilder().name("Borin").stats(new Stats(40, 6, 5)).build(),
                new HeroBuilder().name("Cael").stats(new Stats(24, 9, 2)).build());
    }
}
