package emberdeep.engine.combat.damage;

import java.util.Random;

import emberdeep.engine.character.Fighter;
import lombok.RequiredArgsConstructor;

/**
 * DodgeHandler
 */
@RequiredArgsConstructor 
public class DodgeHandler implements DamageHandler{

    private final Random random;
    private final double chance;
    private final DamageHandler next;

    @Override
    public int handle(Fighter target, Damage damage) {
        if (chance > 0 && random.nextDouble() < chance) {
            return 0;
        }

        return next.handle(target, damage);
    }
}
