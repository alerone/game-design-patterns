package emberdeep.app;

import java.util.Objects;

import emberdeep.engine.combat.damage.DamageFormula;

/**
 * GameConfig
 */
public record GameConfig(long seed, int floors, DamageFormula defaultFormula) {
    public GameConfig {
        if (floors < 1) 
            throw new IllegalArgumentException("floors debe ser al menos 1");
        Objects.requireNonNull(defaultFormula, "defaultFormula");
    }
}
