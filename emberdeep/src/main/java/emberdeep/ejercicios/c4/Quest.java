package emberdeep.ejercicios.c4;

import java.util.Objects;

/**
 * Quest
 */
public record Quest(String title, int reward, int minLevel, String giver) {
    public static class Builder {
        private String title;
        private int reward;
        private int minLevel = 1;
        private String giver = "Anónimo";

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder reward(int reward) {
            this.reward = reward;
            return this;
        }

        public Builder minLevel(int minLevel) {
            this.minLevel = minLevel;
            return this;
        }

        public Builder giver(String giver) {
            this.giver = giver;
            return this;
        }

        public Quest build() {
            if (title == null || title.isBlank()) 
                throw new IllegalStateException("Title no debe estar vacío");
            Objects.requireNonNull(reward, "reward");
            if (reward <= 0) 
                throw new IllegalStateException("Reward debe ser positivo");
            return new Quest(title, reward, minLevel, giver);
        }
    }
}
