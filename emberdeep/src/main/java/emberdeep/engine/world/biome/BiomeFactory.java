package emberdeep.engine.world.biome;

import emberdeep.engine.character.Enemy;
import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.item.Item;
import emberdeep.engine.item.ItemKind;

/**
 * BiomeFactory
 */
public interface BiomeFactory {

    Enemy enemy(EnemyKind kind);
    Item item(ItemKind kind);
}
