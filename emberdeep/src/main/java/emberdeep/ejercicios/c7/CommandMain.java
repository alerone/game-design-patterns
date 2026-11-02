package emberdeep.ejercicios.c7;

import java.util.List;


/**
 * CommandMain
 */
public class CommandMain {

    public static void main(String[] args) {
        Document doc = new Document();
        List<Command> commands = List.of(
                new Insert(doc, 0, "hola mundo"),
                new Insert(doc, 4, ","),
                new Delete(doc, 5, 1),
                new Insert(doc, 5, " querido "));
        for (Command command: commands) {
            command.execute();
            System.out.println("[" + doc.text() + "]");
        }
    }
}
