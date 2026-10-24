package emberdeep.engine.session;

import java.util.List;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Hero;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.world.biome.EnemySpawner;
import lombok.Getter;

/**
 * GameSession
 */
@Getter 
public class GameSession {
    private final List<Fighter> heroes;
    private final List<Fighter> enemies;
    private final Combat combat;

    public GameSession(List<Hero> heroes, EnemySpawner spawner,
            List<EnemyKind> kinds, DamageFormula formula) {
        this.heroes = heroes.stream().map(Fighter::ofHero).toList();
        this.enemies = kinds.stream()
            .map(spawner::spawn)
            .map(Fighter::ofEnemy)
            .toList();
        this.combat = new Combat(this.heroes, this.enemies, formula);
    }

    public int round() { return combat.round(); }
}
