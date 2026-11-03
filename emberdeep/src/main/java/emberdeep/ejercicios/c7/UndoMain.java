package emberdeep.ejercicios.c7;

/**
 * UndoMain
 */
public class UndoMain {

    public static void main(String[] args) {
        Document doc = new Document();
        Editor editor = new Editor();
        editor.run(new Insert(doc, 0, "hola mundo"));
        editor.run(new Insert(doc, 4, ","));
        editor.run(new Delete(doc, 5, 1));
        System.out.println("[" + doc.text() + "]");
        for (int i = 0; i < 3; i++) {
            editor.undo();
            System.out.println("[" + doc.text() + "]");
        }
        System.out.println(editor.undo());
    }
}
