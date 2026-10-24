package emberdeep.ejercicios.c3;

/**
 * Drop
 */
public sealed interface Drop permits Drop.GoldDrop, Drop.PotionDrop, Drop.KeyDrop, Drop.ScrollDrop{
    record GoldDrop(int amount) implements Drop {}

    record PotionDrop(String size) implements Drop {}

    record KeyDrop(int door) implements Drop {}

    record ScrollDrop(String spell) implements Drop {}
}
