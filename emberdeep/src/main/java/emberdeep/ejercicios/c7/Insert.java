package emberdeep.ejercicios.c7;

/**
 * Insert
 */
public class Insert implements Command {
    private final Document doc;
    private final int position;
    private final String text;

    public Insert(Document doc, int position, String text) {
        this.doc = doc;
        this.position = position;
        this.text = text;
    }

    @Override
    public void execute() {
        doc.insert(position, text);
    }
}
