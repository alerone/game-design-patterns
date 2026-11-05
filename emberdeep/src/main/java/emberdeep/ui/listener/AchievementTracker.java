package emberdeep.ui.listener;

import java.util.HashSet;
import java.util.Set;

import emberdeep.engine.event.EventBus;
import emberdeep.engine.event.GameEvent;
import emberdeep.engine.event.GameListener;

/**
 * AchievementTracker
 */
public class AchievementTracker implements GameListener{
    private static final int DODGES_NEEDED = 5;
    private final EventBus bus;
    private final Set<String> unlocked = new HashSet<>();
    private int heroDodges = 0;

    public AchievementTracker(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void onEvent(GameEvent event) {
        switch (event) {
            case GameEvent.DamageDealt e -> {
                if (!e.targetIsHero())
                    unlock("Primera sangre");
            }
            case GameEvent.DamageDodged e -> {
                if (e.targetIsHero()) {
                    heroDodges++;
                    if (heroDodges == DODGES_NEEDED)
                        unlock("Intocable");
                }
            }
            case GameEvent.CombatEnded e -> {
                if (e.heroesWon() && e.heroesHp().contains(1))
                    unlock("Último aliento");
            }
            case GameEvent.FighterDefeated e -> {}
            case GameEvent.Defended e -> {}
            case GameEvent.ItemUsed e -> {}
            case GameEvent.AchievementUnlocked e -> {}
            case null -> {}
            default -> {}
        }
    }

    private void unlock(String achievement) {
        if (unlocked.add(achievement))
            bus.publish(new GameEvent.AchievementUnlocked(achievement));
    }    
}
