package emberdeep.engine.session;

import java.util.List;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Hero;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.item.Inventory;
import emberdeep.engine.item.ItemKind;
import emberdeep.engine.world.biome.BiomeFactory;
import lombok.Getter;

/**
 * GameSession
 */
@Getter 
public class GameSession {
    private final List<Fighter> heroes;
    private final List<Fighter> enemies;
    private final Combat combat;
    private Inventory inventory;

    public GameSession(List<Hero> heroes, BiomeFactory biomeFactory,
            List<EnemyKind> kinds, DamageFormula formula) {
        this.heroes = heroes.stream().map(Fighter::ofHero).toList();
        this.enemies = kinds.stream()
            .map(biomeFactory::enemy)
            .map(Fighter::ofEnemy)
            .toList();
        this.combat = new Combat(this.heroes, this.enemies, formula);

        this.inventory = new Inventory(List.of())
                    .with(biomeFactory.item(ItemKind.HEALING))
                    .with(biomeFactory.item(ItemKind.HEALING))
                    .with(biomeFactory.item(ItemKind.BOMB));
} 

    public void replaceInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int round() { return combat.round(); }
}
