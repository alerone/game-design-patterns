package emberdeep.engine.combat.command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * CommandHistory
 */
public class CommandHistory {

    private final Deque<CombatCommand> done = new ArrayDeque<>();

    public void run(CombatCommand command) {
        command.execute();
        done.push(command);
    }

    public boolean undo() {
        if (done.isEmpty())
            return false;
        done.pop().undo();
        return true;
    }

    public int size() { return done.size(); }
    public void clear() { done.clear(); }
}
