package emberdeep.engine.world.biome;

import java.util.Random;

import emberdeep.engine.character.Enemy;
import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Stats;
import emberdeep.engine.combat.ai.FirstAliveAi;
import emberdeep.engine.combat.ai.LowestHpAi;
import emberdeep.engine.combat.ai.RandomTargetAi;
import emberdeep.engine.item.Item;
import emberdeep.engine.item.ItemKind;
import lombok.RequiredArgsConstructor;

/**
 * CavernBiome
 */
@RequiredArgsConstructor
public class CavernBiome implements BiomeFactory {

    private final Random random;

    @Override
    public Enemy enemy(EnemyKind kind) {
        return switch (kind) {
            case GOBLIN -> new Enemy("Goblin", kind,
                    new Stats(14, 6, 1), new FirstAliveAi());
            case BAT -> new Enemy("Murciélago", kind,
                    new Stats(10, 6, 0), new LowestHpAi());
            case SPIDER -> new Enemy("Araña", kind,
                    new Stats(16, 7, 2), new RandomTargetAi(random));
            case SKELETON, ASH_RAT, ZOMBIE -> throw new IllegalArgumentException(
                    kind + " no vive en la caverna");
        };
    }

    @Override
    public Item item(ItemKind kind) {
        return switch (kind) {
            case HEALING -> new Item("Poción de caverna", kind, 10);
            case BOMB -> new Item("Bomba de roca", kind, 14);
        };
    }

}
