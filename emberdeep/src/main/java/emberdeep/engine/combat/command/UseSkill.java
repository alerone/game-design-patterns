package emberdeep.engine.combat.command;

import java.util.ArrayList;
import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Skill;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.damage.Damage;
import emberdeep.engine.stats.StatSource;

/**
 * UseSkill
 */
public class UseSkill implements CombatCommand{

    private final Combat combat;
    private final Fighter user;
    private final Skill skill;
    private final Fighter target;
    private int hpBefore;
    private int shieldBefore;
    private StatSource statsBefore;

    public UseSkill(Combat combat, Fighter user, Skill skill, Fighter target) {
        this.combat = combat;
        this.user = user;
        this.skill = skill;
        this.target = target;
    }

    @Override
    public void execute() {
        hpBefore = target.hp();
        shieldBefore = target.shield();
        statsBefore = target.stats();
        Damage damage = new Damage(skill.power(), skill.type());
        combat.deal(user, target, damage);
    }

    @Override
    public void undo() {
        target.restore(hpBefore, shieldBefore, statsBefore);
    }
}
