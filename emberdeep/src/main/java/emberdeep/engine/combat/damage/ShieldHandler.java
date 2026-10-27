package emberdeep.engine.combat.damage;

import emberdeep.engine.character.Fighter;
import lombok.RequiredArgsConstructor;

/**
 * ShieldHandler
 */
@RequiredArgsConstructor 
public class ShieldHandler implements DamageHandler{

    private final DamageHandler next;

    @Override
    public int handle(Fighter target, Damage damage) {
        int left = target.absorbShield(damage.amount());
        if (left == 0) {
            return 0;
        }

        return next.handle(target, new Damage(left, damage.type()));
    }

    
}
