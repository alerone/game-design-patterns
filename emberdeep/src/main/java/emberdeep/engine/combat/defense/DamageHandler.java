package emberdeep.engine.combat.defense;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.damage.Damage;

/**
 * DamageHandler
 */
@FunctionalInterface 
public interface DamageHandler {
    int handle(Fighter target, Damage damage);
}
