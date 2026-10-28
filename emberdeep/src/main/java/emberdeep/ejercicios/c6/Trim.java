package emberdeep.ejercicios.c6;

/**
 * Trim
 */
public class Trim extends TextDecorator{

    public Trim(TextSource inner) {
        super(inner);
    }

    @Override
    public String text() {
        return inner.text().trim();
    }
}
