package emberdeep.engine.stats;

import emberdeep.engine.character.Stats;

/**
 * BaseStats
 */
public record BaseStats(Stats stats) implements StatSource {
    @Override public int attack() { return stats.attack(); }
    @Override public int defense() { return stats.defense(); }
    @Override public int maxHp() { return stats.maxHp(); }
    @Override public int armor() { return 0; }
    @Override public StatSource tick() { return this; }
}
