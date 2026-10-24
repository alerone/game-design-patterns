package emberdeep.ejercicios.c2;

/**
 * ScoreMain
 */
public class ScoreMain {

    public static void main(String[] args) {
        ScoreRule rule = args.length > 0 && args[0].equals("hard")
                ? new HardScore()
                : new EasyScore();
        ScoreBoard board = new ScoreBoard(rule);
        System.out.println(board.score(4, 30));
    }
}
