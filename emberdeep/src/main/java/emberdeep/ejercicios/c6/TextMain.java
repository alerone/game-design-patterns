package emberdeep.ejercicios.c6;

/**
 * TextMain
 */
public class TextMain {

    public static void main(String[] args) {
        TextSource raw = new PlainText("   hola    ");
        TextSource a = new Prefix(">> ", new Upper(new Trim(raw)));
        TextSource b = new Trim(new Upper(new Prefix(">> ", raw)));
        System.out.println("[" + a.text() + "]");
        System.out.println("[" + b.text() + "]");
    }
}
