package emberdeep.ejercicios.c9;

import java.util.ArrayList;
import java.util.List;

/**
 * ShotBus
 */
public class ShotBus {

    private final List<ShotListener> listeners = new ArrayList<>();

    public void subscribe(ShotListener listener) {
        listeners.add(listener);
    }

    public void publish(ShotEvent event) {
        for (ShotListener listener : List.copyOf(listeners)) {
            listener.onEvent(event);
        }
    }
}
