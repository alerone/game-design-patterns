package emberdeep.ejercicios.c6;


/**
 * TextDecorator
 */
public abstract class TextDecorator implements TextSource {

    protected final TextSource inner;

    public TextDecorator(TextSource inner) {
        this.inner = inner;
    }
}
