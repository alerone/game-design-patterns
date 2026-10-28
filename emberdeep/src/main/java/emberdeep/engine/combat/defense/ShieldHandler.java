package emberdeep.engine.combat.defense;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.damage.Damage;

/**
 * ShieldHandler
 */
public class ShieldHandler implements DamageHandler{

    private final DamageHandler next;

    public ShieldHandler(DamageHandler next) {
        this.next = next;
    }

    @Override
    public int handle(Fighter target, Damage damage) {
        int left = target.absorbShield(damage.amount());
        if (left == 0) {
            return 0;
        }

        return next.handle(target, new Damage(left, damage.type()));
    }
}
