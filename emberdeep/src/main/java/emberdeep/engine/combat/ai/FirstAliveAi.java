package emberdeep.engine.combat.ai;

import java.util.List;

import emberdeep.engine.character.Fighter;

/**
 * FirstAliveAi
 */
public class FirstAliveAi implements EnemyAi {

    @Override
    public Fighter choose(Fighter self, List<Fighter> heroes) {
        return heroes.stream().filter(Fighter::isAlive).findFirst().orElseThrow();
    }

    
}
