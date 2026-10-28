package emberdeep.app;

import java.util.List;
import java.util.Random;

import emberdeep.engine.character.Enemy;
import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Hero;
import emberdeep.engine.character.Stats;
import emberdeep.engine.combat.ai.FirstAliveAi;
import emberdeep.engine.combat.damage.Damage;
import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.combat.defense.DamagePipeline;
import emberdeep.engine.stats.ArmorBonus;
import emberdeep.engine.stats.BaseStats;

/**
 * Check06H2
 */
public class Check06H2 {

    public static void main(String[] args) {
        Stats stats = new Stats(30, 8, 3);
        Fighter aria = Fighter.ofHero(new Hero("Aria", stats),
                new ArmorBonus(new BaseStats(stats), 1));
        aria.addShield(3);
        Damage hit = new Damage(10, DamageType.PHYSICAL);
        System.out.println(new DamagePipeline(new Random(1), 1.0).apply(aria, hit)
                + " " + aria.getShield());
        DamagePipeline plain = new DamagePipeline(new Random(1), 0.0);
        System.out.println(plain.apply(aria, hit) + " " + aria.getShield());
        Fighter rat = Fighter.ofEnemy(new Enemy("Rata de ceniza", EnemyKind.ASH_RAT,
                new Stats(8, 5, 0), new FirstAliveAi(), List.of()));
        System.out.println(plain.apply(rat, new Damage(10, DamageType.FIRE)));
        System.out.println(plain.apply(rat, hit));
    }
}
