package emberdeep.ejercicios.c9;


/**
 * TermometerMain
 */
public class TempMain {

    public static void main(String[] args) {
        TemperatureBus bus = new TemperatureBus();
        Thermometer thermometer = new Thermometer(bus, 30);
        HighCounter highCounter = new HighCounter();
        TemperatureListener alarmMessage = (int celsius) -> System.out.println("ALARMA: " + celsius + " grados");

        bus.subscribe(highCounter);
        bus.subscribe(alarmMessage);
        thermometer.measure(23);
        thermometer.measure(31);
        thermometer.measure(28);
        thermometer.measure(35);
        System.out.println("Avisos recibidos por el contador: " + highCounter.count());
    }
}
