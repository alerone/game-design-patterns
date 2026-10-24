package emberdeep.engine.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory
 */
public record Inventory(List<Item> items) {
    public Inventory {
        items = List.copyOf(items);
    }

    public Inventory with(Item item) {
        List<Item> next = new ArrayList<>(items);
        next.add(item);
        return new Inventory(next);
    }

    public Inventory without(Item item) {
        List<Item> next = new ArrayList<>(items);
        next.remove(item);
        return new Inventory(next);
    }
}
