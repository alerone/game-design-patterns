package emberdeep.ejercicios.c7;

import java.util.Random;

public class Replay {
    interface Action {
        void run(State state);
    }

    static class State {
        int hp = 20;
        int gold = 0;
        final Random random;

        State(long seed) {
            random = new Random(seed);
        }
    }

    static Action of(char key) {
        return switch (key) {
            case 'g' -> state -> state.gold += state.random.nextInt(6) + 1;
            case 'h' -> state -> state.hp -= state.random.nextInt(6) + 1;
            case 'r' -> state -> state.hp = Math.min(20, state.hp + 4);
            default -> state -> {
            };
        };
    }

    static String play(long seed, String script) {
        State state = new State(seed);
        for (char key : script.toCharArray()) {
            of(key).run(state);
        }
        return state.hp + " " + state.gold;
    }

    public static void main(String[] args) {
        System.out.println(play(42, "ghgrhgg"));
        System.out.println(play(42, "ghgrhgg"));
        System.out.println(play(7, "ghgrhgg"));
    }
}
