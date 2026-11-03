package emberdeep.ejercicios.c7;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Editor
 */
public class Editor {

    private final Deque<Command> done = new ArrayDeque<>();

    void run (Command command) {
        command.execute();
        done.push(command);
    }

    boolean undo() {
        if (done.isEmpty())
            return false;
        Command command = done.pop();
        command.undo();
        return true;
    }
}
