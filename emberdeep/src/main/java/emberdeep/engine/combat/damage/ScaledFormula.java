package emberdeep.engine.combat.damage;

/**
 * ScaledFormula
 */
public class ScaledFormula implements DamageFormula{

    @Override
    public int damage(int attack, int defense) {
        return Math.max(1, attack * 100 / (100 + defense));
    }

    
}
