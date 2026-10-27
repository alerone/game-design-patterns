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
import emberdeep.engine.combat.ai.RandomTargetAi;
import emberdeep.engine.item.Item;
import emberdeep.engine.item.ItemKind;

/**
 * CryptBiome
 */
public class CryptBiome implements BiomeFactory {

    private final TemplateRegistry registry = new TemplateRegistry();

    public CryptBiome(Random random) {
        registry.register(new EnemyTemplate(EnemyKind.SKELETON, "Esqueleto", 
                    new Stats(18, 7, 2), new RandomTargetAi(random)).
                addSkill(new Skill("Golpe de hueso", 9)));
        registry.register(new EnemyTemplate(EnemyKind.ASH_RAT, "Rata de ceniza", 
                    new Stats(8, 5, 0), new LowestHpAi()).
                addSkill(new Skill("Mordisco sucio", 6)));
        registry.register(new EnemyTemplate(EnemyKind.ZOMBIE, "Zombie", 
                    new Stats(22, 5, 1), new FirstAliveAi()).
                addSkill(new Skill("Abrazo pútrido", 7)));
    }

    @Override
    public Enemy enemy(EnemyKind kind) {
        return registry.get(kind).copy().toEnemy();
    }

    @Override
    public Item item(ItemKind kind) {
        return switch (kind) {
            case HEALING -> new Item("Poción de la cripta", kind, 12);
            case BOMB -> new Item("Bomba de ceniza", kind, 10);
        };
    }

}
