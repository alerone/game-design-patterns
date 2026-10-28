package emberdeep.ejercicios.c6;

import java.util.Random;

public class ChainBug {
    interface Handler {
        int handle(Defender d, int damage);
    }

    static class Defender {
        int shield;
        final int armor;

        Defender(int shield, int armor) {
            this.shield = shield;
            this.armor = armor;
        }
    }

    static Handler dodge(Random random, double chance, Handler next) {
        return (d, dmg) -> random.nextDouble() < chance ? 0
                : next.handle(d,
                        dmg);
    }

    static Handler shield(Handler next) {
        return (d, dmg) -> {
            int absorbed = Math.min(d.shield, dmg);
            d.shield -= absorbed;
            return next.handle(d, dmg - absorbed);
        };
    }

    static Handler armor(Handler next) {
        return (d, dmg) -> next.handle(d, Math.max(1, dmg - d.armor));
    }

    static Handler pipeline(Random random, double chance) {
        Handler end = (d, dmg) -> dmg;
        return armor(shield(dodge(random, chance, end)));
    }

    public static void main(String[] args) {
        Defender a = new Defender(4, 2);
        System.out.println("pipeline chance 100%: " + pipeline(new Random(1), 1.0).handle(a, 10) + " shield: "
                + a.shield);
        Defender b = new Defender(3, 2);
        System.out.println("pipeline chance 100%: " + pipeline(new Random(1), 0.0).handle(b, 3) + " shield: " +
                b.shield);
        Defender c = new Defender(4, 2);
        System.out.println("pipeline chance   0%: " + pipeline(new Random(1), 0.0).handle(c, 10) + " shield: "
                + c.shield);
    }
}
