package emberdeep.engine.combat;

import java.util.ArrayList;
import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.damage.Damage;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.combat.defense.DamagePipeline;

/**
 * Combat
 */
public class Combat {

    private final List<Fighter> heroes;
    private final List<Fighter> enemies;
    private final DamageFormula formula;
    private final DamagePipeline pipeline;
    private int round = 1;

    public Combat(List<Fighter> heroes, List<Fighter> enemies, DamageFormula formula,
            DamagePipeline pipeline) {
        this.heroes = heroes;
        this.enemies = enemies;
        this.formula = formula;
        this.pipeline = pipeline;
    }

    public int round() {
        return round;
    }

    public boolean heroesWon() {
        return enemies.stream().noneMatch(Fighter::isAlive);
    }

    public boolean heroesLost() {
        return heroes.stream().noneMatch(Fighter::isAlive);
    }

    public boolean isOver() {
        return heroesLost() || heroesWon();
    }

    public List<String> heroAttack(Fighter hero, Fighter target) {
        List<String> messages = new ArrayList<>();
        hit(hero, target, damageOf(hero, target), messages);
        return messages;
    }

    public List<String> enemyPhase() {
        List<String> messages = new ArrayList<>();
        for (Fighter enemy: enemies) {
            if (!enemy.isAlive()) {
                continue;
            }
            if (heroesLost()) break;

            Fighter target = enemy.chooseTarget(heroes);
            hit(enemy, target, damageOf(enemy, target), messages);
        }
        round++;
        enemies.forEach(Fighter::tickBuffs);
        heroes.forEach(Fighter::tickBuffs);
        return messages;
    }

    private void hit(Fighter attacker, Fighter target, int damage, List<String> messages) {
        Damage physical = new Damage(damage, DamageType.PHYSICAL);
        int resultDamage = pipeline.apply(target, physical);
        target.takeDamage(resultDamage);
        if (resultDamage == 0) 
            messages.add("%s ataca a %s, pero no le hace daño.".formatted(attacker.getName(), target.getName()));
        else 
            messages.add("%s ataca a %s y le hace %d daño.".formatted(attacker.getName(), target.getName(), resultDamage));
        if (!target.isAlive()) {
            messages.add(target.getName() + " queda fuera de combate.");
        }
    }

    private int damageOf(Fighter attacker, Fighter target) {
        return formula.damage(attacker.getStats().attack(), target.getStats().defense());
    }
}
