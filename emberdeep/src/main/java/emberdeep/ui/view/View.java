package emberdeep.ui.view;

import java.util.Objects;

import emberdeep.ui.output.Output;
import emberdeep.ui.output.Style;

/**
 * View
 */
public abstract class View {
    protected final Output output;

    public View(Output output) {
        this.output = Objects.requireNonNull(output);
    }

    protected void line(String text) {
        output.write(text + "\n");
    }

    protected void line(String text, Style style) {
        output.writeStyled(text, style);
        output.write("\n");
    }
}
