package emberdeep.engine.combat.ai;

import java.util.List;

import emberdeep.engine.character.Fighter;

/**
 * EnemyAi
 */
public interface EnemyAi {

    Fighter choose(Fighter self, List<Fighter> heroes);
}
