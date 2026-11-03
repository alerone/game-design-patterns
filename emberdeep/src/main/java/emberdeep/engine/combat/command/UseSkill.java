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
    public List<String> execute() {
        hpBefore = target.getHp();
        shieldBefore = target.getShield();
        statsBefore = target.getStats();
        Damage damage = new Damage(skill.power(), skill.type());
        int dealt = combat.deal(target, damage);

        List<String> messages = new ArrayList<>();

        String common = "%s usa %s sobre %s".formatted(user.getName(), skill.name(), target.getName());
        
        messages.add(dealt > 0 ? common + " y le hace " + dealt + " de daño." : common + ", pero no le hace daño");
        if (target.getHp() == 0)
            messages.add(target.getName() + " queda fuera de combate.");
        return messages;
    }

    @Override
    public void undo() {
        target.restore(hpBefore, shieldBefore, statsBefore);
    }
}
