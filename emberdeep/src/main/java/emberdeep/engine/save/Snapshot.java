package emberdeep.engine.save;

import java.util.List;

import emberdeep.engine.item.Inventory;

/**
 * Snapshot
 */
public record Snapshot(int floor, int x, int y, int round,
        List<FighterState> fighters, Inventory inventory) {
    public Snapshot {
        fighters = List.copyOf(fighters);
    }
}
