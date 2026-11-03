package emberdeep.engine.session;

import java.util.List;
import java.util.stream.Stream;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Hero;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.damage.DamageFormula;
import emberdeep.engine.combat.defense.DamagePipeline;
import emberdeep.engine.item.Inventory;
import emberdeep.engine.item.ItemKind;
import emberdeep.engine.save.FighterState;
import emberdeep.engine.save.Snapshot;
import emberdeep.engine.stats.ArmorBonus;
import emberdeep.engine.stats.BaseStats;
import emberdeep.engine.stats.StatSource;
import emberdeep.engine.stats.WeaponBonus;
import emberdeep.engine.world.biome.BiomeFactory;
import lombok.Getter;

/**
 * GameSession
 */
@Getter
public class GameSession {
    private final List<Fighter> heroes;
    private final List<Fighter> enemies;
    private final Combat combat;
    private Inventory inventory;
    private int floor = 1;
    private int x; 
    private int y;

    public GameSession(int floor, int x, int y, List<Hero> heroes, BiomeFactory biomeFactory,
            List<EnemyKind> kinds, DamageFormula formula, DamagePipeline pipeline) {
        this.heroes = heroes.stream().map(h -> Fighter.ofHero(h, gearOf(h))).toList();
        this.enemies = kinds.stream()
                .map(biomeFactory::enemy)
                .map(Fighter::ofEnemy)
                .toList();
        this.combat = new Combat(this.heroes, this.enemies, formula, pipeline);

        this.inventory = new Inventory(List.of())
                .with(biomeFactory.item(ItemKind.HEALING))
                .with(biomeFactory.item(ItemKind.HEALING))
                .with(biomeFactory.item(ItemKind.BOMB));
        this.floor = floor;
        this.x = x; 
        this.y = y;
    }

    public void setLocation(int floor, int x, int y) {
        this.floor = floor;
        this.x = x;
        this.y = y;
    }

    public List<Fighter> allFighters() {
        return Stream.concat(heroes.stream(), enemies.stream()).toList();
    }

    public Snapshot createSnapshot() {
        List<FighterState> states = allFighters().stream()
            .map(f -> new FighterState(f.hp(), f.shield(), f.stats()))
            .toList();
        return new Snapshot(floor, x, y, combat.round(), states, inventory);
    }

    public void restore(Snapshot snapshot) {
        List<Fighter> all = allFighters();
        if (snapshot.fighters().size() != all.size()) {
            throw new IllegalArgumentException("La instantánea es de otra partida");
        }

        for (int i = 0; i < all.size(); i++) {
            FighterState state = snapshot.fighters().get(i);
            all.get(i).restore(state.hp(), state.shield(), state.stats());
        }

        combat.restoreRound(snapshot.round());
        inventory = snapshot.inventory();
        setLocation(snapshot.floor(), snapshot.x(), snapshot.y());
    }

    public void replaceInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    private StatSource gearOf(Hero hero) {
        StatSource base = new BaseStats(hero.stats());
        StatSource gear = new WeaponBonus(base, 2);
        gear = new ArmorBonus(gear, 1);
        return gear;
    }

    public int round() {
        return combat.round();
    }
}
