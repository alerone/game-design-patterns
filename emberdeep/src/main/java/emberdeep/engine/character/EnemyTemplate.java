package emberdeep.engine.character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import emberdeep.engine.combat.ai.EnemyAi;
import lombok.Setter;

/**
 * EnemyTemplate
 */
@Setter 
public class EnemyTemplate {

    private final EnemyKind kind;
    private String name;
    private Stats stats;
    private final List<Skill> skills;
    private final EnemyAi ai;

    public EnemyTemplate(EnemyKind kind, String name, Stats stats, EnemyAi ai) {
        this.kind = kind;
        this.name = name;
        this.stats = stats;
        this.skills = new ArrayList<>();
        this.ai = ai;
    }

    private EnemyTemplate(EnemyKind kind, String name, Stats stats, List<Skill> skills, EnemyAi ai) {
        this.kind = kind;
        this.name = name;
        this.stats = stats;
        this.skills = skills;
        this.ai = ai;
    }

    public EnemyTemplate copy() {
        return new EnemyTemplate(kind, name, stats, new ArrayList<>(skills), ai);
    }

    public Enemy toEnemy() {
        return new Enemy(name, kind, stats, ai, List.copyOf(skills));
    }

    public EnemyTemplate asElite() {
        EnemyTemplate elite = copy();
        elite.setName("Élite " + name);
        elite.setStats(new Stats(stats.maxHp() * 3 / 2, stats.attack() + 2, stats.defense() + 1));
        elite.addSkill(new Skill("Furia", 12));
        return elite;
    }

    public EnemyTemplate addSkill(Skill skill) { 
        this.skills.add(skill);
        return this;
    }

    public EnemyKind kind() { return kind; }
    public EnemyAi ai() { return ai; }
    public String name() { return name; }
    public Stats stats() { return stats; }
    public List<Skill> skills() { return Collections.unmodifiableList(skills); }
}
