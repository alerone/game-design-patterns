package emberdeep.ejercicios.c3;

/**
 * Altar
 */
public class Altar extends PotionSource {

    @Override
    protected Potion create() {
        return new Potion("Poción sagrada", 40);
    }
}
