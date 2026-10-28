package emberdeep.ui.input;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * ScriptInput
 */
public class ScriptInput implements InputSource {
    private final List<String> keys;
    private final PrintStream out;
    private int position = 0;

    public ScriptInput(Path file, PrintStream out) throws IOException {
        this.keys = Files.readAllLines(file).stream()
            .map(String::trim)
            .filter(line -> !line.isEmpty() && !line.startsWith("#"))
            .toList();
        this.out = out;
    }


    @Override
    public String next() {
        if (position == keys.size()) {
            throw new IllegalStateException("El guion se ha acabado.");
        }
        String key = keys.get(position++);
        out.println("> " + key);
        return key;
    }
}
