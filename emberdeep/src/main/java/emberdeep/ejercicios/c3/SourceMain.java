package emberdeep.ejercicios.c3;

import java.util.List;

/**
 * SourceMain
 */
public class SourceMain {

    public static void main(String[] args) {
        List<PotionSource> sources = List.of(
                new Chest(),
                new Vendor(),
                new Altar());
        for (PotionSource source : sources) {
            System.out.println(source.open());
        }
    }
}
