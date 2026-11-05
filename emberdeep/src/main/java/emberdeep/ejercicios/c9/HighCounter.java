package emberdeep.ejercicios.c9;

/**
 * HighCounter
 */
public class HighCounter implements TemperatureListener{
    private int measureCount;

    @Override
    public void onHigh(int celsius) {
        measureCount++;
    }

    public int count() { return measureCount; }    
}
