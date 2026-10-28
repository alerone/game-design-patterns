package emberdeep.engine.combat.ai;

import java.util.List;

import emberdeep.engine.character.Fighter;

/**
 * SkillfulAi
 */
public class SkillfulAi implements EnemyAi{
    private EnemyAi choosingAi;

    public SkillfulAi(EnemyAi choosingAi) {
        this.choosingAi = choosingAi;
    }

    @Override
    public Fighter choose(Fighter self, List<Fighter> heroes) {
        return choosingAi.choose(self, heroes);
    }
}
