package emberdeep.engine.combat.damage;

/**
 * DamageFormula
 */
@FunctionalInterface 
public interface DamageFormula {

    int damage(int attack, int defense);
}
