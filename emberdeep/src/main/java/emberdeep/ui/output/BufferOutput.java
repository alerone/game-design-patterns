package emberdeep.ui.output;

/**
 * BufferOutput
 */
public class BufferOutput implements Output {
    private final StringBuilder text = new StringBuilder();

    @Override
    public void write(String part) {
        text.append(part);
    }

    @Override
    public void writeStyled(String part, Style style) {
        text.append(part);
    }

    public String contents() { return text.toString(); }
}
