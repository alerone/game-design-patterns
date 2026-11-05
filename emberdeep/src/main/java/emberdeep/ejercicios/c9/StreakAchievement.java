package emberdeep.ejercicios.c9;

/**
 * StreakAchievement
 */
public class StreakAchievement implements ShotListener{
    private int hitsCount;
    private boolean achieved;

    @Override
    public void onEvent(ShotEvent event) {
        switch (event) {
            case Hit(int damage) -> hitsCount++;
            case Miss() -> hitsCount = 0;
        }

        if (hitsCount == 5 && !achieved) {
            System.out.println("Logro: cinco aciertos seguidos.");
            achieved = true;
        } 
    }
}
