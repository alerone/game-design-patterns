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
        private String map = "";

        public FloorBuilder biome(BiomeFactory biome) {
            this.biome = biome;
            return this;
        }

        public FloorBuilder enemies(List<EnemyKind> enemies) {
            this.enemies = enemies;
            return this;
        }

        public FloorBuilder map(String map) {
            this.map = map;
            return this;
        }

        public DungeonBuilder done() {
            floors.add(new Floor(biome, enemies, map));
            return DungeonBuilder.this;
        }
    }

    public Dungeon build() {
        if (floors == null || floors.isEmpty())
            throw new IllegalStateException("Para crear una dungeon deben haber floors");
        for (int i = 0; i < floors.size(); i++) {
             Floor floor = floors.get(i);
            String label = "La planta " + (i + 1);
            if (floor.biome() == null) 
                throw new IllegalStateException(label + " no tiene bioma");
            if (floor.enemies().isEmpty())
                throw new IllegalStateException(label + " no contiene enemigos");
            if (floor.map() == null || floor.map().isBlank()) 
                throw new IllegalStateException(label + " no tiene mapa");
        }

        return new Dungeon(floors);
    }
}
