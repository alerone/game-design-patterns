package emberdeep.engine.combat.command;

import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.stats.StatKind;
import emberdeep.engine.stats.StatSource;

/**
 * Defend
 */
public class Defend implements CombatCommand{
    private static final int SHIELD = 5;
    private static final int DEFENSE_BONUS = 2;
    private int shieldBefore;
    private StatSource statsBefore;

    private final Fighter user;

    public Defend(Fighter user) { this.user = user; }

    @Override
    public List<String> execute() {
        shieldBefore = user.getShield();
        statsBefore = user.getStats();
        user.addShield(SHIELD);
        user.addBuff(StatKind.DEFENSE, DEFENSE_BONUS, 1);
        return List.of(user.getName() + " se cubre: " + SHIELD 
                + " de escudo y +" + DEFENSE_BONUS
                + " de defensa este turno.");
    }

    @Override
    public void undo() {
        user.restore(user.getHp(), shieldBefore, statsBefore);
    }
}
