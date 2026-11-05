package emberdeep.ejercicios.c9;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Listener> listeners = new ArrayList<>();

    public void subscribe(Listener listener) {
        listeners.add(listener);
    }

    public void post(String message) {
        for (Listener listener : List.copyOf(listeners)) {
            listener.onMessage(message);
        }
    }

    public void unsubscribe(Listener listener) { listeners.remove(listener); } 
}
