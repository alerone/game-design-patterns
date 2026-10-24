package emberdeep.ejercicios.c2;

/**
 * ScoreBoard
 */
public class ScoreBoard {
    private final ScoreRule rule; 
     
    public ScoreBoard(ScoreRule rule) {this.rule = rule;} 

    public int score(int kills, int secondsLeft) {
        return rule.score(kills, secondsLeft);
    }
}
