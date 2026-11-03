package emberdeep.ejercicios.c7;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * HealMain
 */
public class HealMain {

    public static void main(String[] args) {
        Unit unit = new Unit(18, 14);
        Random random = new Random();
        System.out.println("Unit hp: " + unit.hp() + "/18");

        Deque<Command> heals = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            Command heal = new HealNaive(unit, random);
            heals.push(heal);
            heal.execute();
            System.out.println("[HEAL] Unit hp: " + unit.hp() + "/18");
        }

        for (int i = 0; i < heals.size(); i++) {
            Command heal = heals.pop();
            heal.undo();
            System.out.println("[UNDO] Unit hp: " + unit.hp() + "/18");
        }
    }
}
