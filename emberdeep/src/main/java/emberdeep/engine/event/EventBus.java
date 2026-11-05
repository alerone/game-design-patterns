package emberdeep.engine.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * EventBus
 */
public class EventBus {

    private final List<GameListener> listeners = new ArrayList<>();

    public void subscribe(GameListener listener) {
        listeners.add(Objects.requireNonNull(listener));
    }

    public void unsubscribe(GameListener listener) {
        listeners.remove(listener);
    }

    public void publish(GameEvent event) {
        for (GameListener listener : List.copyOf(listeners)) {
            listener.onEvent(event);
        }
    }
}
