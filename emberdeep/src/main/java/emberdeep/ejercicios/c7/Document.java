package emberdeep.ejercicios.c7;

/**
 * Document
 */
public class Document {

    private StringBuilder text = new StringBuilder();

    void insert(int position, String s) { text.insert(position, s); }
    void delete(int position, int length) { text.delete(position, position + length); }
    String text() { return text.toString(); } 
    String substring(int start, int length) { return text().substring(start, start + length); } 
}
