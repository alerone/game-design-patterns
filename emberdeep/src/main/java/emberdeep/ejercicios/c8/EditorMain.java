package emberdeep.ejercicios.c8;

/**
 * EditorMain
 */
public class EditorMain {

    public static void main(String[] args) {
        Editor editor = new Editor();
        History history = new History();
        editor.type("hola");
        history.save(editor.snapshot());
        editor.type(" mundo");
        history.save(editor.snapshot());
        editor.moveTo(0);
        editor.type(">> ");
        show(editor);
        editor.restore(history.pop());
        show(editor);
        editor.restore(history.pop());
        show(editor);
    }

    private static void show(Editor editor) {
        System.out.println("[" + editor.text() + "] cursor=" + editor.cursor());
    }
}
