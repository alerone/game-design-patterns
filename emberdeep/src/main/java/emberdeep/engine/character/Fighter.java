package emberdeep.engine.character;

import lombok.Getter;

/**
 * Fighter
 */
@Getter 
public class Fighter {

    private final String name;
    private final Stats stats;
    private int hp;

    private Fighter(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        this.hp = stats.maxHp();
    }

    public static Fighter ofHero(Hero hero) {
        return new Fighter(hero.name(), hero.stats());
    }

    public static Fighter ofEnemy(Enemy enemy) {
        return new Fighter(enemy.name(), enemy.stats());
    }

    public boolean isAlive() { return hp > 0; }
    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }
}
