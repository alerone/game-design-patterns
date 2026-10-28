package emberdeep.engine.combat.command;

import java.util.List;

/**
 * Command
 */
public interface CombatCommand {

    List<String> execute();
    void undo();
}
