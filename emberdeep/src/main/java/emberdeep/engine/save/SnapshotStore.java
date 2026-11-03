package emberdeep.engine.save;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * SnapshotStore
 */
public class SnapshotStore {

    private final Deque<Snapshot> checkpoints = new ArrayDeque<>();

    public void push(Snapshot snapshot) {
        checkpoints.push(snapshot);
    }

    public Snapshot last() {
        if (checkpoints.isEmpty()) {
            throw new IllegalArgumentException("No hay puntos de control");
        }

        return checkpoints.peek();
    }

    public boolean isEmpty() { return checkpoints.isEmpty(); }
    public int size() { return checkpoints.size(); }
}
