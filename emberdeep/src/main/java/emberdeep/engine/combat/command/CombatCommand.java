package emberdeep.engine.combat.command;

import java.util.List;

/**
 * Command
 */
public interface CombatCommand {

    void execute();
    void undo();
}
