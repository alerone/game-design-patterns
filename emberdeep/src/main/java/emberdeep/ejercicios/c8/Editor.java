package emberdeep.ejercicios.c8;

public class Editor {
    private String text = "";
    private int cursor;

    public void type(String s) {
        // inserta s donde está el cursor
        text = text.substring(0, cursor) + s + text.substring(cursor);
        cursor += s.length();
    }

    public void moveTo(int position) {
        // el cursor no sale del texto
        cursor = Math.max(0, Math.min(position, text.length()));
    }

    public String text() {
        return text;
    }

    public EditorState snapshot() {
        return new EditorState(text, cursor);
    }

    public void restore(EditorState state) {
        this.text = state.text();
        this.cursor = state.cursor();
    }

    public int cursor() {
        return cursor;
    }
}
