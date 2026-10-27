package emberdeep.engine.character;

import java.util.List;

import emberdeep.engine.combat.ai.EnemyAi;
import emberdeep.engine.stats.BaseStats;
import emberdeep.engine.stats.StatSource;
import lombok.Getter;

/**
 * Fighter
 */
@Getter 
public class Fighter {

    private final String name;
    private EnemyAi ai;
    private int hp;
    private int shield;
    private StatSource gear;
    private StatSource stats;

    private Fighter(String name, StatSource gear, EnemyAi ai) {
        this.name = name;
        this.stats = gear;
        this.gear = gear;
        this.ai = ai;
        this.hp = stats.maxHp();
    }

    public static Fighter ofHero(Hero hero, StatSource gear) {
        return new Fighter(hero.name(), gear, null);
    }

    public static Fighter ofEnemy(Enemy enemy) {
        return new Fighter(enemy.name(), new BaseStats(enemy.stats()), enemy.ai());
    }

    public void addBuff(StatKind stat, int bonus, int turns) {
        stats = new TempBuff(stats, stat, bonus, turns);
    }

    public void tickBuffs() {
        stats = stats.tick();
    }

    public void addShield(int amount) {
        shield += amount;
    }
    
    public int absorbShield(int damage) {
        int absorbed = Math.min(shield, damage);
        shield -= absorbed;
        return damage - absorbed;
    }

    public boolean isAlive() { return hp > 0; }
    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    public Fighter chooseTarget(List<Fighter> heroes) {
        if (ai == null) {
            throw new IllegalStateException(name + " no tiene IA: es un héroe");
        }

        return ai.choose(this, heroes);
    }
}
