package emberdeep.ejercicios.c2;

/**
 * EasyScore
 */
public class EasyScore implements ScoreRule{

    @Override
    public int score(int kills, int secondsLeft) {
        return kills * 10;
    }

    
}
