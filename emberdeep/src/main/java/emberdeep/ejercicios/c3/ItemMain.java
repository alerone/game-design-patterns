package emberdeep.ejercicios.c3;

/**
 * ItemMain
 */
public class ItemMain {

    public static void main(String[] args) {
        Drop gold = new Drop.GoldDrop(25);
        Drop potion = new Drop.PotionDrop("grande");
        Drop key = new Drop.KeyDrop(3);
        Drop scroll = new Drop.ScrollDrop("fuego");

        System.out.println(ItemFactory.create(gold));
        System.out.println(ItemFactory.create(potion));
        System.out.println(ItemFactory.create(key));
        System.out.println(ItemFactory.create(scroll));
    }
}
