package emberdeep.engine.world.biome;

import java.util.Random;

import emberdeep.engine.character.Enemy;
import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.EnemyTemplate;
import emberdeep.engine.character.Skill;
import emberdeep.engine.character.Stats;
import emberdeep.engine.character.TemplateRegistry;
import emberdeep.engine.combat.ai.FirstAliveAi;
import emberdeep.engine.combat.ai.LowestHpAi;
import emberdeep.engine.item.Item;
import emberdeep.engine.item.ItemKind;

/**
 * CavernBiome
 */
public class CavernBiome implements BiomeFactory {

    private final TemplateRegistry registry = new TemplateRegistry();

    public CavernBiome(Random random) {
        registry.register(new EnemyTemplate(EnemyKind.GOBLIN, "Goblin", 
                    new Stats(14, 6, 1), new FirstAliveAi()).
                addSkill(new Skill("Puñalada", 8)));
        registry.register(new EnemyTemplate(EnemyKind.BAT, "Murciélago", 
                    new Stats(10, 6, 0), new LowestHpAi()).
                addSkill(new Skill("Chillido", 6)));
        registry.register(new EnemyTemplate(EnemyKind.SPIDER, "Araña", 
                    new Stats(16, 7, 2), new FirstAliveAi()).
                addSkill(new Skill("Picadura", 9)));
    }
    @Override
    public Enemy enemy(EnemyKind kind) {
        return registry.get(kind).copy().toEnemy();
    }

    @Override
    public Item item(ItemKind kind) {
        return switch (kind) {
            case HEALING -> new Item("Poción de caverna", kind, 10);
            case BOMB -> new Item("Bomba de roca", kind, 14);
        };
    }

}
