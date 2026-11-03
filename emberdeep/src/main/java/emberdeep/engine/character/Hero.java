package emberdeep.engine.character;

import java.util.List;

/**
 * Hero
 */
public record Hero(String name, Stats stats, List<Skill> skills) {
    public Hero {
        skills = List.copyOf(skills);
    }
}
