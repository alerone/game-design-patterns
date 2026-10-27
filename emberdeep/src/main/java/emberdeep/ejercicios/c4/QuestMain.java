package emberdeep.ejercicios.c4;

/**
 * QuestMain
 */
public class QuestMain {

    public static void main(String[] args) {
        Quest q1 = new Quest.Builder()
            .title("Limpiar la cripta")
            .reward(50)
            .build();

        Quest q2 = new Quest.Builder()
            .title("Matar al rey goblin")
            .reward(200)
            .minLevel(3)
            .giver("Lucía")
            .build();

        System.out.println(q1);
        System.out.println(q2);

        try {
            new Quest.Builder()
                .title("Sin premio")
                .minLevel(3)
                .giver("Lucía")
                .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
