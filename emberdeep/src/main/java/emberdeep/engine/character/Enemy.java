package emberdeep.engine.character;

import emberdeep.engine.combat.ai.EnemyAi;

/**
 * Enemy
 */
public record Enemy(String name, Stats stats, EnemyAi ai) {
}
