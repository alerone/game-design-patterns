package emberdeep.engine.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import emberdeep.engine.character.Fighter;

/**
 * Combat
 */
public class Combat {

    private final List<Fighter> heroes;
    private final List<Fighter> enemies;
    private int round = 1;

    public Combat(List<Fighter> heroes, List<Fighter> enemies) {
        this.heroes = heroes;
        this.enemies = enemies;
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

            Fighter target;
            int damage;
            if (enemy.getName().equals("Goblin")){
                target = firstAlive(heroes);
                damage = damageOf(enemy, target);
            } else if (enemy.getName().equals("Rata de ceniza")) {
                target = weakest(heroes);
                damage = damageOf(enemy, target);
            } else if (enemy.getName().equals("Esqueleto")) {
                target = firstAlive(heroes);
                damage = 3;
            } else {
                target = firstAlive(heroes);
                damage = damageOf(enemy, target);
            }

            hit(enemy, target, damage, messages);
        }
        round++;
        return messages;
    }

    private void hit(Fighter attacker, Fighter target, int damage, List<String> messages) {
        target.takeDamage(damage);
        messages.add("%s ataca a %s y le hace %d daño.".formatted(attacker.getName(), target.getName(), damage));
    }

    private static int damageOf(Fighter attacker, Fighter target) {
        return Math.max(1, attacker.getStats().attack() - target.getStats().defense());
    }

    private static Fighter firstAlive(List<Fighter> group) {
        return group.stream().filter(Fighter::isAlive).findFirst()
            .orElseThrow();
    }

    private static Fighter weakest(List<Fighter> group) {
        return group.stream().filter(Fighter::isAlive)
            .min(Comparator.comparingInt(Fighter::getHp)).orElseThrow();
   }
}
