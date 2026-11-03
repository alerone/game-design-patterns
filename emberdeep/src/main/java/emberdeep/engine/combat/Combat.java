package emberdeep.engine.combat;

import java.util.ArrayList;
import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.command.CombatCommand;
import emberdeep.engine.combat.command.CommandHistory;
import emberdeep.engine.combat.damage.Damage;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.combat.defense.DamagePipeline;

/**
 * Combat
 */
public class Combat {

    private final List<Fighter> heroes;
    private final List<Fighter> enemies;
    private final DamageFormula formula;
    private final DamagePipeline pipeline;
    private final CommandHistory history = new CommandHistory(); 
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

    public void restoreRound(int newRound) {
        if (newRound < 1) return;
        this.round = newRound;
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

    public List<String> enemyPhase() {
        List<String> messages = new ArrayList<>();
        for (Fighter enemy: enemies) {
            if (!enemy.isAlive()) {
                continue;
            }
            if (heroesLost()) break;

            messages.addAll(enemy.act(this, heroes).execute());
        }
        round++;
        enemies.forEach(Fighter::tickBuffs);
        heroes.forEach(Fighter::tickBuffs);
        history.clear();
        return messages;
    }

    public List<String> perform(CombatCommand command) {
        return history.run(command);
    }

    public boolean undo() {
        return history.undo();
    } 

    public int damageOf(Fighter attacker, Fighter target) {
        return formula.damage(attacker.stats().attack(), target.stats().defense());
    }

    public int deal(Fighter target, Damage damage) {
        int dealt = pipeline.apply(target, damage);
        target.takeDamage(dealt);
        return dealt;
    }
}
