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
 * CryptBiome
 */
@RequiredArgsConstructor
public class CryptBiome implements BiomeFactory {

    private final Random random;

    @Override
    public Enemy enemy(EnemyKind kind) {
        return switch (kind) {
            case SKELETON -> new Enemy("Esqueleto", kind,
                    new Stats(18, 7, 2), new RandomTargetAi(random));
            case ASH_RAT -> new Enemy("Rata de ceniza", kind,
                    new Stats(8, 5, 0), new LowestHpAi());
            case ZOMBIE -> new Enemy("Zombi", kind,
                    new Stats(22, 5, 1), new FirstAliveAi());
            case GOBLIN, BAT, SPIDER -> throw new IllegalArgumentException(
                    kind + " no vive en la cripta");
        };
    }

    @Override
    public Item item(ItemKind kind) {
        return switch (kind) {
            case HEALING -> new Item("Poción de la cripta", kind, 12);
            case BOMB -> new Item("Bomba de ceniza", kind, 10);
        };
    }

}
