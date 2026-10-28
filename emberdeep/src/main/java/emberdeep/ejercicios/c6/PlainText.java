package emberdeep.ejercicios.c6;

/**
 * PlainText
 */
public class PlainText implements TextSource{
    private final String text;

    public PlainText(String text) {
        this.text = text;
    }

    @Override
    public String text() {
        return text;
    }

    
}
