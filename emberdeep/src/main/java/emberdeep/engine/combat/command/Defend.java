package emberdeep.engine.combat.command;

import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.event.GameEvent;
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
    private final Combat combat;

    private final Fighter user;

    public Defend(Combat combat, Fighter user) { 
        this.combat = combat;
        this.user = user; 
    }

    @Override
    public void execute() {
        shieldBefore = user.shield();
        statsBefore = user.stats();
        user.addShield(SHIELD);
        user.addBuff(StatKind.DEFENSE, DEFENSE_BONUS, 1);
        combat.publish(new GameEvent.Defended(user.name(), user.shield(), DEFENSE_BONUS));
    }

    @Override
    public void undo() {
        user.restore(user.hp(), shieldBefore, statsBefore);
    }
}
