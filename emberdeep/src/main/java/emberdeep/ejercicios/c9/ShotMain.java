package emberdeep.ejercicios.c9;

/**
 * ShotMain
 */
public class ShotMain {

    public static void main(String[] args) {
        ShotBus bus = new ShotBus();
        Archer archer = new Archer(bus);
        bus.subscribe(event -> {
            if (event instanceof Miss) {
                System.out.println("Fallo");
            }
        });
        bus.subscribe(new StreakAchievement());

        boolean[] shots = { true, true, false, true, true, true, true,
                true, false, true, true, true, true, true };
        for (boolean hits : shots) {
            archer.shoot(hits);
        }
    }
}
