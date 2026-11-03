package emberdeep.ejercicios.c7;

import java.util.Random;

/**
 * HealNaive
 */
public class HealNaive implements Command {
    private final Unit unit;
    private final Random random;
    private int healed;

    public HealNaive(Unit unit, Random random) {
        this.unit = unit;
        this.random = random;
    }

    @Override
    public void execute() {
        int roll = random.nextInt(6) + 1;
        healed = unit.heal(roll);
    }

    @Override
    public void undo() {
        unit.hurt(healed);
    }
}
