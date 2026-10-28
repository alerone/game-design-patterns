package emberdeep.engine.character;

import java.util.List;

import emberdeep.engine.combat.ai.EnemyAi;
import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.stats.BaseStats;
import emberdeep.engine.stats.StatKind;
import emberdeep.engine.stats.StatSource;
import emberdeep.engine.stats.TempBuff;
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
    private EnemyKind kind;

    private Fighter(String name, StatSource gear, EnemyAi ai, EnemyKind kind) {
        this.name = name;
        this.stats = gear;
        this.gear = gear;
        this.ai = ai;
        this.hp = gear.maxHp();
        this.kind = kind;
    }

    public static Fighter ofHero(Hero hero, StatSource gear) {
        return new Fighter(hero.name(), gear, null, null);
    }

    public static Fighter ofEnemy(Enemy enemy) {
        return new Fighter(enemy.name(), new BaseStats(enemy.stats()), enemy.ai(), enemy.kind());
    }

    public void restore(int hp, int shield, StatSource stats) {
        this.hp = hp;
        this.shield = shield;
        this.stats = stats;
    }

    public int heal(int amount) {
        int healed = Math.min(amount, stats.maxHp() - hp);
        hp += healed;
        return healed;
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

    public boolean resists(DamageType type) {
        return kind != null && kind.resistances().contains(type);
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
