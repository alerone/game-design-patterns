package emberdeep.engine.world.biome;

import emberdeep.engine.character.Enemy;
import emberdeep.engine.character.EnemyKind;

/**
 * EnemySpawner
 */
public abstract class EnemySpawner {

    public final Enemy spawn(EnemyKind kind) {
        Enemy enemy = create(kind);
        if (enemy.ai() == null) {
            throw new IllegalArgumentException(enemy.name() + " nace sin IA");
        }
        return enemy;
    }

    protected abstract Enemy create(EnemyKind kind);
}
