package emberdeep.ejercicios.c6;

/**
 * Prefix
 */
public class Prefix extends TextDecorator{
    private final String prefix;

    public Prefix(String prefix, TextSource inner) {
        super(inner);
        this.prefix = prefix;
    }

    @Override
    public String text() {
        return prefix + inner.text();
    }
}
