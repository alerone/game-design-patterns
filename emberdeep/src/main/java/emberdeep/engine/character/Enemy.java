package emberdeep.engine.character;

import emberdeep.engine.combat.ai.EnemyAi;

/**
 * Enemy
 */
public record Enemy(String name, EnemyKind kind, Stats stats, EnemyAi ai) {
}
