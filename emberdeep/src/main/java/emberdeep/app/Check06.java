package emberdeep.app;

import emberdeep.engine.character.Stats;
import emberdeep.engine.stats.ArmorBonus;
import emberdeep.engine.stats.BaseStats;
import emberdeep.engine.stats.StatKind;
import emberdeep.engine.stats.StatSource;
import emberdeep.engine.stats.TempBuff;
import emberdeep.engine.stats.WeaponBonus;

/**
 * Check06
 */
public class Check06 {

    public static void main(String[] args) {
        StatSource hero = new BaseStats(new Stats(30, 8, 3));
        hero = new WeaponBonus(hero, 2);
        hero = new ArmorBonus(hero, 1);
        printStats(hero);
        hero = new TempBuff(hero, StatKind.ATTACK, 4, 2);
        System.out.println("Added +4 ATK TempBuff");
        System.out.println("Turns Left: " + 2);
        printAttack(hero);
        hero = hero.tick();
        System.out.println("Turns Left: " + 1);
        printAttack(hero);
        hero = hero.tick();
        System.out.println("Turns Left: " + 0);
        printAttack(hero);
        printStats(hero);
    }

    private static void printStats(StatSource hero) {
        System.out.println("ATK: " + hero.attack() + ", DEF: " + hero.defense() + ", ARM: " 
                + hero.armor());
    }

    private static void printAttack(StatSource hero) {
        System.out.println("ATK: " + hero.attack());
    }
}
