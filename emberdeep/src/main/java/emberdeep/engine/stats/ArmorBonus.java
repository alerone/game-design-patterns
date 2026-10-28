package emberdeep.engine.stats;

/**
 * ArmorBonus
 */
public class ArmorBonus extends StatDecorator {
    private final int bonus;

    public ArmorBonus(StatSource inner, int bonus) {
        super(inner);
        this.bonus = bonus;
    }

    public int armor() {
        return inner.armor() + bonus;
    }

    @Override
    protected StatSource rewrap(StatSource newInner) {
        return new ArmorBonus(newInner, bonus);
    }
}
