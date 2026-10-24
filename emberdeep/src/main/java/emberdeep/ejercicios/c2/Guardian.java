package emberdeep.ejercicios.c2;

/**
 * Guardian
 */
public class Guardian {

    private final String name;
    private final int maxHp;
    private final Behavior opening;
    private final Behavior defensive;
    private Behavior currentBehavior;
    private int hp;

    public Guardian(String name, int maxHp,
            Behavior opening, Behavior defensive) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.opening = opening;
        this.defensive = defensive;
        this.currentBehavior = opening;
    }

    void setBehavior(Behavior behavior) { this.currentBehavior = behavior; } 

    String act() {
        return currentBehavior.act(name);
    }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
        if ((hp * 100 / maxHp) < 30) {
            this.currentBehavior = defensive;
        } 
    }
}
