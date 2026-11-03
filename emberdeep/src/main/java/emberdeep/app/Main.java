package emberdeep.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Hero;
import emberdeep.engine.character.HeroBuilder;
import emberdeep.engine.character.Skill;
import emberdeep.engine.character.Stats;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.combat.damage.FlatFormula;
import emberdeep.engine.combat.damage.ScaledFormula;
import emberdeep.engine.combat.defense.DamagePipeline;
import emberdeep.engine.session.GameSession;
import emberdeep.engine.world.Dungeon;
import emberdeep.engine.world.DungeonBuilder;
import emberdeep.engine.world.Floor;
import emberdeep.engine.world.biome.BiomeFactory;
import emberdeep.engine.world.biome.CavernBiome;
import emberdeep.engine.world.biome.CryptBiome;
import emberdeep.engine.world.map.MapData;
import emberdeep.engine.world.map.MapParser;
import emberdeep.engine.world.map.TileTypes;
import emberdeep.ui.ConsoleUi;
import emberdeep.ui.input.ConsoleInput;
import emberdeep.ui.input.InputSource;
import emberdeep.ui.input.ScriptInput;

/**
 * Main
 */
public class Main {
    private final GameConfig config;

    private Main(GameConfig config) { this.config = config; }

    public static void main(String[] args) throws IOException {
        GameConfig config = new GameConfig(42L, 3, new FlatFormula());
        new Main(config).play(args);
    }
    private void play(String[] args) throws IOException {
        String script = scriptOf(args);
        String[] options = optionsOf(args);
        int floorNumber = chooseFloor(options);

        InputSource input = script == null
            ? new ConsoleInput(new Scanner(System.in), System.out)
            : new ScriptInput(Path.of(script), System.out);
        ConsoleUi ui = new ConsoleUi(input, System.out);
        Random random = new Random(config.seed());


        BiomeFactory crypt = new CryptBiome(random);
        BiomeFactory cavern = new CavernBiome(random);

        List<EnemyKind> cryptEnemies = List.of(EnemyKind.SKELETON, EnemyKind.ASH_RAT, EnemyKind.ZOMBIE);
        List<EnemyKind> cavernEnemies = List.of(EnemyKind.GOBLIN, EnemyKind.BAT, EnemyKind.SPIDER);

        Dungeon dungeon = new DungeonBuilder()
            .floor().biome(crypt).enemies(cryptEnemies).map(MapData.floor(1)).done()
            .floor().biome(cavern).enemies(cavernEnemies).map(MapData.floor(2)).done()
            .floor().biome(crypt).enemies(cryptEnemies).map(MapData.floor(3)).done().build();
        Floor floor = dungeon.floors().get(floorNumber - 1);

        if (options.length > 1 && options[1].equals("map")) {
            explore(ui, dungeon, floorNumber);
            return;
        }

        DamageFormula formula = chooseFormula(ui, options, script != null);
        DamagePipeline pipeline = new DamagePipeline(random, 0.0);
        GameSession session = new GameSession(heroes(), floor.biome(), 
                floor.enemies(), formula, pipeline);

        ui.run(session);
    }

    public void explore(ConsoleUi ui, Dungeon dungeon, int firstFloor) {
        TileTypes types = new TileTypes();
        MapParser parser = new MapParser(types);
        int number = firstFloor;
        while (true) {
            Floor floor = dungeon.floors().get(number - 1);
            boolean descend = ui.explore(parser.parse(floor.map()), types);

            if (!descend || number == dungeon.floors().size()) 
                return;

            number++;
        }
    }

    private static String scriptOf(String[] args) {
        return Arrays.stream(args)
            .filter(arg -> arg.endsWith(".txt"))
            .findFirst().orElse(null);
    }

    private static String[] optionsOf(String[] args) {
        return Arrays.stream(args)
            .filter(arg -> !arg.endsWith(".txt"))
            .toArray(String[]::new);
    }


    private DamageFormula chooseFormula(ConsoleUi ui, String[] args, boolean scripted) {
        boolean useDefault = scripted 
            || args.length > 1 && args[1].equals("default");
        if (useDefault)
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
                new HeroBuilder().name("Aria").stats(new Stats(30, 8, 3))
                    .skill(new Skill("Golpe poderoso", 12, DamageType.PHYSICAL))
                    .skill(new Skill("Llamarada", 12, DamageType.FIRE))
                    .build(),
                new HeroBuilder().name("Borin").stats(new Stats(40, 6, 5))
                    .skill(new Skill("Golpe poderoso", 12, DamageType.PHYSICAL))
                    .skill(new Skill("Llamarada", 12, DamageType.FIRE)).build(),
                new HeroBuilder().name("Cael").stats(new Stats(24, 9, 2))
                    .skill(new Skill("Golpe poderoso", 12, DamageType.PHYSICAL))
                    .skill(new Skill("Llamarada", 12, DamageType.FIRE)).build());
    }
}
