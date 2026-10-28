package emberdeep.engine.combat.defense;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.damage.Damage;
import emberdeep.engine.combat.damage.DamageType;

/**
 * ResistanceHandler
 */
public class ResistanceHandler implements DamageHandler {

    private final DamageHandler next;

    public ResistanceHandler(DamageHandler next) {
        this.next = next;
    }

    @Override
    public int handle(Fighter target, Damage damage) {
        DamageType type = damage.type();
        int resistDamage = target.resists(type) 
            ? Math.max(1, damage.amount() / 2) 
            : damage.amount(); 
        return next.handle(target, new Damage(resistDamage, type));
    }
}
