package emberdeep.engine.combat.ai;

import java.util.Comparator;
import java.util.List;

import emberdeep.engine.character.Fighter;

/**
 * WeakestAi
 */
public class LowestHpAi implements EnemyAi{

    @Override
    public Fighter choose(Fighter self, List<Fighter> heroes) {
        return heroes.stream().filter(Fighter::isAlive)
            .min(Comparator.comparingInt(Fighter::getHp)).orElseThrow();
    }
}
