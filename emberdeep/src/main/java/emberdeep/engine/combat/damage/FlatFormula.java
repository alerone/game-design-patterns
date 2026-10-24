package emberdeep.engine.combat.damage;

/**
 * FlatFormula
 */
public class FlatFormula implements DamageFormula{

    @Override
    public int damage(int attack, int defense) {
        return Math.max(1, attack - defense);
    }

    
}
