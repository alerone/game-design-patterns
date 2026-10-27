package emberdeep.engine.stats;

/**
 * StatSource
 */
public interface StatSource {

    int attack();
    int defense();
    int maxHp();
    int armor();
    StatSource tick();
}
