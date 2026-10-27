package emberdeep.engine.character;

import java.util.List;

import emberdeep.engine.combat.ai.EnemyAi;

/**
 * Enemy
 */
public record Enemy(String name, EnemyKind kind, Stats stats, EnemyAi ai, List<Skill> skills) {
}
