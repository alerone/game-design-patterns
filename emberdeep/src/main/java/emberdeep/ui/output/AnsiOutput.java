package emberdeep.ui.output;

import java.io.PrintStream;

/**
 * AnsiOutput
 */
public class AnsiOutput implements Output{
    private static final String ESC = "\u001B[";
    private static final String RESET = ESC + "0m";
    private static final String RED = ESC + "31m";
    private static final String GREEN = ESC + "32m";
    private static final String YELLOW = ESC + "33m";

    private final PrintStream out;

    public AnsiOutput(PrintStream out) {
        this.out = out;
    }

    @Override
    public void write(String text) {
        out.print(text);
    }

    @Override
    public void writeStyled(String text, Style style) {
        String code = codeOf(style);
        if (code.isEmpty()) {
            out.print(text);
        } else {
            out.print(code + text + RESET);
        }
    }

    private static String codeOf(Style style) {
        return switch (style) {
            case DANGER -> RED;
            case GOOD -> GREEN;
            case MEDAL -> YELLOW;
            case NORMAL -> "";
        };
    }

    
}
