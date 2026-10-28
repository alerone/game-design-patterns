package emberdeep.engine.combat.defense;

import java.util.Random;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.damage.Damage;

/**
 * DamagePipeline
 */
public class DamagePipeline {

    private final DamageHandler first;

    public DamagePipeline(Random random, double dodgeChance) {
        DamageHandler end = (target, damage) -> damage.amount();
        this.first = new DodgeHandler(random, dodgeChance, 
                new ShieldHandler(
                    new ArmorHandler(
                        new ResistanceHandler(end))));
    }

    public int apply(Fighter target, Damage damage) {
        return first.handle(target, damage);
    }
}
