package emberdeep.engine.combat.command;

import java.util.ArrayList;
import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.damage.Damage;
import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.stats.StatSource;

/**
 * Attack
 */
public class Attack implements CombatCommand {
    private final Combat combat;
    private final Fighter attacker;
    private final Fighter target;
    private int hpBefore;
    private int shieldBefore;
    private StatSource statsBefore;

    public Attack(Combat combat, Fighter attacker, Fighter target) {
        hpBefore = target.hp();
        shieldBefore = target.shield();
        statsBefore = target.stats();

        this.combat = combat;
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public void execute() {
        int raw = combat.damageOf(attacker, target);
        combat.deal(attacker, target, 
                new Damage(raw, DamageType.PHYSICAL));
    }

    @Override
    public void undo() {
        target.restore(hpBefore, shieldBefore, statsBefore);
    }
}
