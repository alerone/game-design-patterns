package emberdeep.ejercicios.c3;

/**
 * Chest
 */
public class Chest extends PotionSource {

    @Override 
    public Potion create() {
        return new Potion("Poción menor", 10);
    }
}
