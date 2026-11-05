package emberdeep.engine.event;

import java.util.List;

import emberdeep.engine.combat.damage.DamageType;

/**
 * GameEvent
 */
public sealed interface GameEvent {
    record DamageDealt(String attacker, String target,
            boolean targetIsHero, int amount, DamageType type,
            int targetHp, int targetMaxHp)
            implements GameEvent {}
    record DamageDodged(String attacker, String target,
            boolean targetIsHero) implements GameEvent {}
    record FighterDefeated(String name, boolean isHero)
            implements GameEvent {}
    record Defended(String name, int shield, int defenseBonus)
            implements GameEvent {}

    record ItemUsed(String user, String target, String itemName,
            int targetHp, int targetMaxHp) implements GameEvent {}
    record CombatEnded(boolean heroesWon, List<Integer> heroesHp) implements GameEvent {
        public CombatEnded {
            heroesHp = List.copyOf(heroesHp);
        }
    }

    record AchievementUnlocked(String name) implements GameEvent {}
}
