package emberdeep.engine.stats;

/**
 * TempBuff
 */
public class TempBuff extends StatDecorator {
    private final int bonus;
    private final StatKind stat;
    private final int turnsLeft;

    public TempBuff(StatSource inner, StatKind stat, int bonus, int turnsLeft) {
        super(inner);
        this.stat = stat;
        this.bonus = bonus;
        this.turnsLeft = turnsLeft;
    }

 
    @Override
    public int attack() {
        return super.attack() + (stat==StatKind.ATTACK ? bonus : 0);
    }


    @Override
    public int defense() {
        return super.defense() + (stat==StatKind.DEFENSE ? bonus : 0);
    }


    @Override
    public StatSource tick() {
        StatSource next = inner.tick();
        if (turnsLeft <= 1) 
            return next;
        return new TempBuff(next, stat, bonus, turnsLeft - 1);
    }

    

    @Override
    public String toString() {
        return "TurnsLeft: " + turnsLeft + " Stat: " + stat;
    }


    @Override
    protected StatSource rewrap(StatSource newInner) {
        return new TempBuff(newInner, stat, bonus, turnsLeft);
    }
}
