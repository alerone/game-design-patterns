package emberdeep.engine.combat.damage;

import emberdeep.engine.character.Fighter;

/**
 * DamageHandler
 */
@FunctionalInterface 
public interface DamageHandler {
    int handle(Fighter target, Damage damage);
}
