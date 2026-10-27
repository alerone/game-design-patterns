package emberdeep.engine.world;

import java.util.List;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.world.biome.BiomeFactory;

/**
 * Floor
 */
public record Floor(BiomeFactory biome, List<EnemyKind> enemies) {
    public Floor {
        enemies = List.copyOf(enemies);
    }
}
