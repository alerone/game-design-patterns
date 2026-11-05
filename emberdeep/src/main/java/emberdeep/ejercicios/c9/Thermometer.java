package emberdeep.ejercicios.c9;

/**
 * Thermometer
 */
public class Thermometer {

    private final TemperatureBus eventBus;
    private final int limit;

    public Thermometer(TemperatureBus eventBus, int limit) {
        this.eventBus = eventBus;
        this.limit = limit;
    }

    public void measure(int celsius) { 
        if(celsius > limit)
            eventBus.publish(celsius);
    }
}
