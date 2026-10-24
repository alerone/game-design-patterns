package emberdeep.engine.combat.ai;

import java.util.List;
import java.util.Random;

import emberdeep.engine.character.Fighter;

/**
 * RandomTargetAi
 */
public class RandomTargetAi implements EnemyAi {
    private final Random random;

    public RandomTargetAi(Random random) {this.random = random;}

    @Override
    public Fighter choose(Fighter self, List<Fighter> heroes) {
        List<Fighter> alive = heroes.stream().filter(Fighter::isAlive).toList();
        return alive.get(random.nextInt(alive.size()));
    }
}
