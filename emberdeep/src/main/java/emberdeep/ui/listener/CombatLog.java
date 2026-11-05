package emberdeep.ui.listener;

import java.io.PrintStream;

import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.event.GameEvent;
import emberdeep.engine.event.GameListener;
import emberdeep.engine.event.GameEvent.*;
import emberdeep.utils.TermColors;
/**
 * CombatLog
 */
public class CombatLog implements GameListener {
    private final PrintStream out;

    public CombatLog(PrintStream out) {
        this.out = out;
    }

    @Override
    public void onEvent(GameEvent event) {
        switch (event) {
            case DamageDealt e -> out.printf("%s hace %d de daño %s a %s.\n", e.attacker(), e.amount(),
                kind(e.type()), e.target());
            case DamageDodged e -> out.println("El golpe de " + e.attacker() + 
                " a " + e.target() + " no hace daño.");
            case FighterDefeated e -> out.println(e.name() + " queda fuera de combate.");
            case Defended e -> out.println(e.name() + " se cubre: " + e.shield() + 
                " de escudo y + " + e.defenseBonus() + " de defensa este turno.");
            case ItemUsed e -> out.println(
                    e.user() + " usa " + e.itemName() + " sobre " + e.target() + ".");
            case CombatEnded e -> out.println(e.heroesWon() ? "Victoria" : "Derrota...");
            case AchievementUnlocked e -> out.println(
                    TermColors.toYellow("Logro desbloqueado: " + e.name() + "!"));
        };
    }

    private String kind(DamageType type) {
        return switch(type) {
            case FIRE -> "de fuego";
            case PHYSICAL -> "físico";
        };
    }

    
}
