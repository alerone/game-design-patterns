package emberdeep.ejercicios.c4;

public class MapRegistry {
    private static int created = 0;

    class Holder {
        private static MapRegistry INSTANCE = new MapRegistry();
    }

    private MapRegistry() {
        created++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public static MapRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public static int created() {
        return created;
    }
}
