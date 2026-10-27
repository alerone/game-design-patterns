package emberdeep.engine.world;

import java.util.ArrayList;
import java.util.List;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.world.biome.BiomeFactory;

/**
 * DungeonBuilder
 */
public class DungeonBuilder {

    private final List<Floor> floors = new ArrayList<>();

    public FloorBuilder floor() { return new FloorBuilder(); }

    public class FloorBuilder {
        private BiomeFactory biome;
        private List<EnemyKind> enemies;

        public FloorBuilder biome(BiomeFactory biome) {
            this.biome = biome;
            return this;
        }

        public FloorBuilder enemies(List<EnemyKind> enemies) {
            this.enemies = enemies;
            return this;
        }

        public DungeonBuilder done() {
            floors.add(new Floor(biome, enemies));
            return DungeonBuilder.this;
        }
    }

    public Dungeon build() {
        if (floors == null || floors.isEmpty())
            throw new IllegalStateException("Para crear una dungeon deben haber floors");
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).enemies().isEmpty())
                throw new IllegalStateException("La planta " + (i + 1) + " no contiene enemigos");
        }

        return new Dungeon(floors);
    }
}
