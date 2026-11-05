package emberdeep.ejercicios.c9;

/**
 * Welcome
 */
public class Welcome implements Listener{
    private final Board board;

    public Welcome(Board board) {
        this.board = board;
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Bienvenida: " + message);
        board.unsubscribe(this);
    }
}
