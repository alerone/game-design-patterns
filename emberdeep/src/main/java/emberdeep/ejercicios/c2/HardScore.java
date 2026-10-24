package emberdeep.ejercicios.c2;

/**
 * HardScore
 */
public class HardScore implements ScoreRule{

    @Override
    public int score(int kills, int secondsLeft) {
        return kills * 25 + secondsLeft;
    }

    
}
