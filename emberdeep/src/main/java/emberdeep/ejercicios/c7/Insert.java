package emberdeep.ejercicios.c7;

/**
 * Insert
 */
public class Insert implements Command {
    private final Document doc;
    private final int position;
    private final String text;
    private boolean executed = false;

    public Insert(Document doc, int position, String text) {
        this.doc = doc;
        this.position = position;
        this.text = text;
    }

    @Override
    public void execute() {
        executed = true;
        doc.insert(position, text);
    }

    @Override
    public void undo() {
        if (executed) {
            doc.delete(position, text.length());
            executed = false;
        }
    }
}
