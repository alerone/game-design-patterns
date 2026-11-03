package emberdeep.engine.save;
/**
 *
 * SnapshotTest
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Hero;
import emberdeep.engine.character.HeroBuilder;
import emberdeep.engine.character.Stats;
import emberdeep.engine.combat.damage.FlatFormula;
import emberdeep.engine.combat.defense.DamagePipeline;
import emberdeep.engine.item.Inventory;
import emberdeep.engine.session.GameSession;
import emberdeep.engine.world.biome.CryptBiome;

class SnapshotTest {

    private static GameSession newSession() {
        Random random = new Random(42);
        List<Hero> heroes = List.of(
                new HeroBuilder().name("Aria")
                    .stats(new Stats(30, 8, 3)).build());
        return new GameSession(1,0,0, heroes, new CryptBiome(random), List.of(EnemyKind.SKELETON),
                new FlatFormula(), new DamagePipeline(random, 0.0));
    }

    @Test 
    void restoreBringsBackSavedState() {
        GameSession session = newSession();
        Fighter aria = session.heroes().get(0);
        aria.addShield(5);
        Snapshot saved = session.createSnapshot();

        aria.takeDamage(10);
        aria.absorbShield(5);
        session.replaceInventory(new Inventory(List.of()));
        session.restore(saved);


        assertEquals(30, aria.hp());
        assertEquals(5, aria.shield());
        assertEquals(3, session.inventory().items().size());
    }


    @Test 
    void emptyStoreHasNoLastCheckpoint() {
        SnapshotStore store = new SnapshotStore();
        assertThrows(IllegalArgumentException.class, () -> store.last());
    }
}
