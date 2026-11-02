package emberdeep.ejercicios.c7;

/**
 * Delete
 */
public class Delete implements Command{
    private final Document doc;
    private final int position;
    private final int length;


    public Delete(Document doc, int position, int length) {
        this.length = length;
        this.doc = doc;
        this.position = position;
    }


    @Override
    public void execute() {
        doc.delete(position, length);
    }
}

