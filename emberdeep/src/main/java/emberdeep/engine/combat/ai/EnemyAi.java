package emberdeep.engine.combat.ai;

import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.command.Attack;
import emberdeep.engine.combat.command.CombatCommand;

/**
 * EnemyAi
 */
public interface EnemyAi {

    Fighter choose(Fighter self, List<Fighter> heroes);

    default CombatCommand act(Combat combat, Fighter self, 
            List<Fighter> heroes){
        return new Attack(combat, self, choose(self, heroes));
    }
}
