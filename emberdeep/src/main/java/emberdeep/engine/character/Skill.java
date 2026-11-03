package emberdeep.engine.character;

import emberdeep.engine.combat.damage.DamageType;

/**
 * Skill
 */
public record Skill(String name, int power, DamageType type) {
}
