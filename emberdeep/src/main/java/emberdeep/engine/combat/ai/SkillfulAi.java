package emberdeep.engine.combat.ai;

import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.command.Attack;
import emberdeep.engine.combat.command.CombatCommand;
import emberdeep.engine.combat.command.UseSkill;

/**
 * SkillfulAi
 */
public class SkillfulAi implements EnemyAi{
    private EnemyAi targeting;

    public SkillfulAi(EnemyAi targeting) {
        this.targeting = targeting;
    }

    @Override
    public Fighter choose(Fighter self, List<Fighter> heroes) {
        return targeting.choose(self, heroes);
    }

    @Override
    public CombatCommand act(Combat combat, Fighter self, List<Fighter> heroes) {
        Fighter target = choose(self, heroes);
        if (combat.round() % 3 == 0 && !self.skills().isEmpty())
            return new UseSkill(combat, self, self.skills().get(0), target);

        return new Attack(combat, self, target);
    }
}
