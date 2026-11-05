package emberdeep.ui.listener;

import java.io.PrintStream;
import java.util.List;

import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.event.GameEvent;
import emberdeep.engine.event.GameListener;
import emberdeep.utils.TermColors;

/**
 * HudListener
 */
public class HudListener implements GameListener {
    private final PrintStream out;

    public HudListener(PrintStream out) {
        this.out = out;
    }

    @Override
    public void onEvent(GameEvent event) {
        switch (event) {
            case GameEvent.DamageDealt(String attacker, String target, boolean targetIsHero, int amount, DamageType type, int targetHp, int targetMaxHp) ->
                showHud(target, targetHp, targetMaxHp);
            case GameEvent.DamageDodged(String attacker, String target, boolean targetIsHero) -> {
            }
            case GameEvent.FighterDefeated(String name, boolean isHero) -> {
            }
            case GameEvent.Defended(String name, int shield, int defenseBonus) -> {
            }
            case GameEvent.ItemUsed(String user, String target, String itemName, int targetHp, int targetMaxHp) ->
                showHud(target, targetHp, targetMaxHp);
            case GameEvent.CombatEnded(boolean heroesWon, List<Integer> heroesHp) -> {
            }
            case GameEvent.AchievementUnlocked(String name) -> {
            }
            case null -> {
            }
            default -> {
            }
        }
    }

    private void showHud(String target, int targetHp, int targetMaxHp) {
        int remaining = 10 * targetHp / targetMaxHp;
        String lifeRemaining = TermColors.toRed("#".repeat(remaining))
                + ".".repeat(10 - remaining);
        out.printf("  %s [%s] %d/%d\n", target, lifeRemaining, targetHp, targetMaxHp);
    }
}
