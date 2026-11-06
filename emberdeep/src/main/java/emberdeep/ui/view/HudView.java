package emberdeep.ui.view;

import emberdeep.engine.event.GameEvent;
import emberdeep.engine.event.GameListener;
import emberdeep.ui.output.Output;
import emberdeep.ui.output.Style;

/**
 * HudView
 */
public class HudView extends View implements GameListener{
    private static final int WIDTH = 10;
    
    public HudView(Output output) { super(output); }

    @Override
    public void onEvent(GameEvent event) {
        switch (event) {
            case GameEvent.DamageDealt e -> bar(e.target(), e.targetHp(), 
                    e.targetMaxHp());
            case GameEvent.ItemUsed e -> bar(e.target(), e.targetHp(), 
                    e.targetMaxHp());
            case GameEvent.DamageDodged e -> {}
            case GameEvent.FighterDefeated e -> {}
            case GameEvent.Defended e -> {}
            case GameEvent.CombatEnded e -> {}
            case GameEvent.AchievementUnlocked e -> {}
        };
    }

    private void bar(String name, int hp, int maxHp) {
        int filled = (hp * WIDTH + maxHp - 1) / maxHp;
        line(" " + name + " [" + "#".repeat(filled)
                + ".".repeat(WIDTH - filled) + "] " + hp + "/" + maxHp,
                styleOf(hp, maxHp));
    }

    private static Style styleOf(int hp, int maxHp) {
        if (hp * 4 <= maxHp) 
            return Style.DANGER;
        return hp * 2 > maxHp ? Style.GOOD : Style.NORMAL; 
    }
}
