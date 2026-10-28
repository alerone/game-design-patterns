package emberdeep.engine.character;

import java.util.Set;

import emberdeep.engine.combat.damage.DamageType;

/**
 * EnemyKind
 */
public enum EnemyKind {
    SKELETON, ASH_RAT(Set.of(DamageType.FIRE)), ZOMBIE, // cripta
    GOBLIN, BAT, SPIDER; // caverna

    private final Set<DamageType> resistances;
    EnemyKind() {
        this(Set.of());
    }
    EnemyKind(Set<DamageType> resistances) {
        this.resistances = resistances;
    }

    public Set<DamageType> resistances() {
        return resistances;
    }
}
