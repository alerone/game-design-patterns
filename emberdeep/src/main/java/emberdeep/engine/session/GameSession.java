package emberdeep.engine.session;

import java.util.List;

import emberdeep.engine.character.Enemy;
import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Hero;
import emberdeep.engine.combat.Combat;
import lombok.Getter;

/**
 * GameSession
 */
@Getter 
public class GameSession {
    private final List<Fighter> heroes;
    private final List<Fighter> enemies;
    private final Combat combat;

    public GameSession(List<Hero> heroes, List<Enemy> floorEnemies) {
        this.heroes = heroes.stream().map(Fighter::ofHero).toList();
        this.enemies = floorEnemies.stream().map(Fighter::ofEnemy).toList();
        this.combat = new Combat(this.heroes, this.enemies);
    }
    public int round() { return combat.round(); }
}
