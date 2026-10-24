package emberdeep.engine.character;

import java.util.List;

import emberdeep.engine.combat.ai.EnemyAi;
import lombok.Getter;

/**
 * Fighter
 */
@Getter 
public class Fighter {

    private final String name;
    private final Stats stats;
    private EnemyAi ai;
    private int hp;

    private Fighter(String name, Stats stats, EnemyAi ai) {
        this.name = name;
        this.stats = stats;
        this.ai = ai;
        this.hp = stats.maxHp();
    }

    public static Fighter ofHero(Hero hero) {
        return new Fighter(hero.name(), hero.stats(), null);
    }

    public static Fighter ofEnemy(Enemy enemy) {
        return new Fighter(enemy.name(), enemy.stats(), enemy.ai());
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
