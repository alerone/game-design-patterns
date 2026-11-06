package emberdeep.ui.output;

import java.io.PrintStream;

/**
 * PlainOutput
 */
public class PlainOutput implements Output {
    private final PrintStream out;

    public PlainOutput(PrintStream out) {
        this.out = out;
    }

    @Override
    public void write(String text) {
        out.print(text);
    }

    @Override
    public void writeStyled(String text, Style style) {
        write(text);
    }
}
