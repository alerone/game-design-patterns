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
        hpBefore = target.getHp();
        shieldBefore = target.getShield();
        statsBefore = target.getStats();

        this.combat = combat;
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public List<String> execute() {
        int raw = combat.damageOf(attacker, target);
        int dealt = combat.deal(target, 
                new Damage(raw, DamageType.PHYSICAL));
        String who = attacker.getName() + " ataca a " + target.getName();
        List<String> messages = new ArrayList<>();
        messages.add(dealt == 0 
                ? who + ", pero no le hace daño."
                : who + " y le hace " + dealt + " de daño.");
        if (!target.isAlive()) {
            messages.add(target.getName() + " queda fuera de combate.");
        }

        return messages;
    }

    @Override
    public void undo() {
        target.restore(hpBefore, shieldBefore, statsBefore);
    }
}
