package emberdeep.ejercicios.c4;

public class MapMain {
    public static void main(String[] args) throws InterruptedException {
        MapRegistry[] seen = new MapRegistry[2];
        Thread a = new Thread(() -> seen[0] = MapRegistry.getInstance());
        Thread b = new Thread(() -> seen[1] = MapRegistry.getInstance());
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println((seen[0] == seen[1]) + " " + MapRegistry.created());
    }
}
