package emberdeep.ejercicios.c9;

import java.util.ArrayList;
import java.util.List;

/**
 * TemperatureBus
 */
public class TemperatureBus {

    private final List<TemperatureListener> eventBus = new ArrayList<>();


    public void subscribe(TemperatureListener listener) {
        eventBus.add(listener);
    }

    public void publish(int celsius) {
        eventBus.stream().forEach(listener -> listener.onHigh(celsius));
    }
}
