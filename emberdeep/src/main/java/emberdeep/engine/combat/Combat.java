package emberdeep.engine.combat;

import java.util.ArrayList;
import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Hero;
import emberdeep.engine.combat.command.CombatCommand;
import emberdeep.engine.combat.command.CommandHistory;
import emberdeep.engine.combat.damage.Damage;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.combat.defense.DamagePipeline;
import emberdeep.engine.event.EventBus;
import emberdeep.engine.event.GameEvent;

/**
 * Combat
 */
public class Combat {

    private final List<Fighter> heroes;
    private final List<Fighter> enemies;
    private final DamageFormula formula;
    private final DamagePipeline pipeline;
    private final CommandHistory history = new CommandHistory();
    private final EventBus eventBus;
    private boolean ended;
    private int round = 1;

    public Combat(List<Fighter> heroes, List<Fighter> enemies, DamageFormula formula,
            DamagePipeline pipeline, EventBus bus) {
        this.heroes = heroes;
        this.enemies = enemies;
        this.formula = formula;
        this.pipeline = pipeline;
        this.eventBus = bus;
    }

    public int round() {
        return round;
    }

    public void restoreRound(int newRound) {
        if (newRound < 1)
            return;
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

    public void enemyPhase() {
        for (Fighter enemy : enemies) {
            if (!enemy.isAlive()) {
                continue;
            }
            if (heroesLost())
                break;
            perform(enemy.act(this, heroes));
        }
        round++;
        enemies.forEach(Fighter::tickBuffs);
        heroes.forEach(Fighter::tickBuffs);
        history.clear();
        checkEnd();
    }

    private void checkEnd() {
        if (heroesWon() && !ended) {
            publish(new GameEvent.CombatEnded(
                    heroesWon(), heroes.stream().map(Fighter::hp).toList()));
            ended = true;
        }
    }

    public void perform(CombatCommand command) {
        history.run(command);
        checkEnd();
    }

    public boolean undo() {
        return history.undo();
    }

    public int damageOf(Fighter attacker, Fighter target) {
        return formula.damage(attacker.stats().attack(), target.stats().defense());
    }

    public int deal(Fighter attacker, Fighter target, Damage damage) {
        int dealt = pipeline.apply(target, damage);
        target.takeDamage(dealt);
        boolean isHero = heroes.contains(target);
        if (dealt <= 0)
            publish(new GameEvent.DamageDodged(attacker.name(), target.name(), isHero));
        else
            publish(new GameEvent.DamageDealt(attacker.name(), target.name(), isHero, dealt, damage.type(),
                    target.hp(), target.stats().maxHp()));
        if (!target.isAlive())
            publish(new GameEvent.FighterDefeated(target.name(), isHero));
        return dealt;
    }

    public void publish(GameEvent event) {
        eventBus.publish(event);
    }
}
