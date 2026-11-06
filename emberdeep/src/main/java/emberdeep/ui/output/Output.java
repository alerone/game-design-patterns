package emberdeep.ui.output;

/**
 * Output
 */
public interface Output {

    void write(String text);

    void writeStyled(String text, Style style);
}
