package emberdeep.ejercicios.c3;

/**
 * Vendor
 */
public class Vendor extends PotionSource{

    @Override
    protected Potion create() {
        return new Potion("Poción mayor", 25);
    }
}
