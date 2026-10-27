package emberdeep.engine.world;

import java.util.List;

/**
 * Dungeon
 */
public record Dungeon(List<Floor> floors) {
    public Dungeon {
        floors = List.copyOf(floors);
    }
}
