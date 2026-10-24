package emberdeep.ejercicios.c3;

/**
 * PotionSource
 */
public abstract class PotionSource {

    public final String open() {
        Potion potion = create();
        return "Obtienes " + potion.name() + " (+" + potion.heal() + ")";
    }

    protected abstract Potion create();
    
}
