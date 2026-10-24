package emberdeep.ejercicios.c3;

/**
 * ItemFactory
 */
public class ItemFactory {

    public static Item create(Drop drop) {
        return switch (drop) {
            case Drop.GoldDrop(int amount) -> new Item("Oro", amount);
            case Drop.PotionDrop(String size) -> new Item("Poción " + size, 10);
            case Drop.KeyDrop(int door) -> new Item("Llave " + door, 1);
            case Drop.ScrollDrop(String scroll) -> new Item("Pergamino de " + scroll, 5);
        };
    }
}
