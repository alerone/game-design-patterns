package emberdeep.engine.stats;

/**
 * WeaponBonus
 */
public class WeaponBonus extends StatDecorator {
    private final int bonus;

    public WeaponBonus(StatSource inner, int bonus) {
        super(inner);
        this.bonus = bonus;
    }

    @Override
    public int attack() {
        return inner.attack() + bonus;
    }

    @Override
    protected StatSource rewrap(StatSource newInner) {
        return new WeaponBonus(newInner, bonus);
    }

    
}
