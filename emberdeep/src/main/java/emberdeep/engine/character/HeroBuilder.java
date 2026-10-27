package emberdeep.engine.character;

/**
 * HeroBuilder
 */
public class HeroBuilder {

    private String name;
    private Stats stats;

    public HeroBuilder name(String name) {
        this.name = name;
        return this;
    }

    public HeroBuilder stats(Stats stats) {
        this.stats = stats;
        return this;
    }

    public Hero build() {
        if (name == null || name.isBlank())
            throw new IllegalStateException("El héroe necesita un nombre");
        if (stats == null) 
            throw new IllegalStateException("el héroe necesita estadísticas");
        if (stats.maxHp() <= 0 || stats.attack() <= 0 || stats.defense() < 0) 
            throw new IllegalStateException("Estadísticas inválidas: " + stats);
        return new Hero(name, stats);
    }
}

