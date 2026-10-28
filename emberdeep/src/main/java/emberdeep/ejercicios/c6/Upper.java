package emberdeep.ejercicios.c6;

/**
 * Upper
 */
public class Upper extends TextDecorator {

    public Upper(TextSource inner) {
        super(inner);
    }

    @Override
    public String text() {
        return inner.text().toUpperCase();
    }
}
