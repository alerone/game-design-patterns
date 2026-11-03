package emberdeep.ejercicios.c7;

/**
 * Unit
 */
public class Unit {

    private final int maxHp;
    private int hp;

    public Unit(int maxHp, int hp) {
        this.maxHp = maxHp;
        this.hp = hp;
    }

    public int hp() {
        return hp;
    }

    public int heal(int amount) {
        int healed = Math.min(amount, maxHp - hp);
        hp += healed;
        return healed;
    }

    public void hurt(int amount) {
        hp = Math.max(0, hp - amount);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
