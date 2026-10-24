package emberdeep.engine.world.biome;

import java.util.Random;

import emberdeep.engine.character.Enemy;
import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Stats;
import emberdeep.engine.combat.ai.FirstAliveAi;
import emberdeep.engine.combat.ai.LowestHpAi;
import emberdeep.engine.combat.ai.RandomTargetAi;

/**
 * CryptSpawner
 */
public class CryptSpawner extends EnemySpawner{
    private final Random random;

    public CryptSpawner(Random random) {
        this.random = random;
    }

    @Override
    protected Enemy create(EnemyKind kind) {
        return switch(kind) {
            case SKELETON -> new Enemy("Esqueleto", kind, 
                new Stats(18, 7, 2), new RandomTargetAi(random));
            case ASH_RAT -> new Enemy("Rata de ceniza", kind,
                new Stats(8,5,0), new LowestHpAi());
            case ZOMBIE -> new Enemy("Zombi", kind,
                new Stats(22, 5, 1), new FirstAliveAi());
            case GOBLIN, BAT, SPIDER -> 
                throw new IllegalArgumentException(kind + " no vive en la cripta");
        };
    }
}
