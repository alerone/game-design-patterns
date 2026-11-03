package emberdeep.ejercicios.c8;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * History
 */
public class History {

    private final Deque<EditorState> checkpoints = new ArrayDeque<>();

    public void save(EditorState state) {checkpoints.push(state);}
    public EditorState pop() { return checkpoints.pop(); }
    public boolean isEmpty() { return checkpoints.isEmpty(); }
}
