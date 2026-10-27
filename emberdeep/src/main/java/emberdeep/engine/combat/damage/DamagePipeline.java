package emberdeep.engine.combat.damage;

import java.util.Random;

import emberdeep.engine.character.Fighter;

/**
 * DamagePipeline
 */
public class DamagePipeline {

    private final DamageHandler first;

    public DamagePipeline(Random random, double dodgeChance) {
        DamageHandler end = (target, damage) -> damage.amount();
        this.first = new DodgeHandler(random, dodgeChance, new ShieldHandler(end));
    }

    public int apply(Fighter target, Damage damage) {
        return first.handle(target, damage);
    }
}
