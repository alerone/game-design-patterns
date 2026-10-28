package emberdeep.ui.input;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * ConsoleInput
 */
public class ConsoleInput implements InputSource {

    private final Scanner in;
    private final PrintStream out;

    public ConsoleInput(Scanner input, PrintStream out) {
        this.in = input;
        this.out = out;
    }

    @Override
    public String next() {
        out.println("> ");
        out.flush();
        return in.nextLine().trim();
    }
}
