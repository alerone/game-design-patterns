package emberdeep.ejercicios.c9;

/**
 * Archer
 */
public class Archer {

    private final ShotBus bus;

    public Archer(ShotBus bus) {
        this.bus = bus;
    }
    
    public void shoot(boolean hits) {
        bus.publish(hits ? new Hit(4) : new Miss());
    }
}
