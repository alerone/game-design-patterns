package emberdeep.engine.combat.command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * CommandHistory
 */
public class CommandHistory {

    private final Deque<CombatCommand> done = new ArrayDeque<>();

    public List<String> run(CombatCommand command) {
        List<String> messages = command.execute();
        done.push(command);
        return messages;
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
