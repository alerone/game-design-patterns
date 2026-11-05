package emberdeep.ejercicios.c9;

/**
 * WelcomeMain
 */
public class WelcomeMain {

    public static void main(String[] args) {
        Board board = new Board();
        board.subscribe(m -> System.out.println("Pantalla: " + m));
        board.subscribe(new Welcome(board));
        board.subscribe(m -> System.out.println("Archivo: " + m));
        board.post("hola");
        board.post("adios");
    }
}
