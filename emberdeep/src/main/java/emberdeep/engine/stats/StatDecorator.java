package emberdeep.engine.stats;

/**
 * StatDecorator
 */
public abstract class StatDecorator implements StatSource {
    protected final StatSource inner;

    protected StatDecorator(StatSource inner) {
        this.inner = inner;
    }

    @Override
    public int attack() {
        return inner.attack();
    }

    @Override
    public int defense() {
        return inner.defense();
    }

    @Override
    public int maxHp() {
        return inner.maxHp();
    }

    @Override
    public int armor() {
        return inner.armor();
    }

    @Override
    public StatSource tick() {
        StatSource next = inner.tick();
        return next == inner ? this : rewrap(next);
    }

    protected abstract StatSource rewrap(StatSource newInner);
}
