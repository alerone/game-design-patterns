package emberdeep.engine.combat.defense;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.damage.Damage;

/**
 * ArmorHandler
 */
public class ArmorHandler implements DamageHandler {
    private final DamageHandler next;

    public ArmorHandler(DamageHandler next) { this.next = next; }

    @Override
    public int handle(Fighter target, Damage damage) {
        int reduced = Math.max(1, damage.amount() - target.getStats().armor());
        return next.handle(target, new Damage(reduced, damage.type()));
    }
}
