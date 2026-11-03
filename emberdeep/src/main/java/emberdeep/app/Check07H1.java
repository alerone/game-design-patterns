package emberdeep.app;

import java.util.List;
import java.util.Random;

import emberdeep.engine.character.EnemyKind;
import emberdeep.engine.character.Fighter;
import emberdeep.engine.character.Hero;
import emberdeep.engine.character.HeroBuilder;
import emberdeep.engine.character.Skill;
import emberdeep.engine.character.Stats;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.command.Attack;
import emberdeep.engine.combat.command.CombatCommand;
import emberdeep.engine.combat.command.Defend;
import emberdeep.engine.combat.command.UseItem;
import emberdeep.engine.combat.command.UseSkill;
import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.combat.damage.FlatFormula;
import emberdeep.engine.combat.defense.DamagePipeline;
import emberdeep.engine.item.Item;
import emberdeep.engine.session.GameSession;
import emberdeep.engine.world.biome.CryptBiome;

public class Check07H1 {
    public static void main(String[] args) {
        Random random = new Random(42);
        Skill flare = new Skill("Llamarada", 10, DamageType.FIRE);
        Hero aria = new HeroBuilder().name("Aria")
                .stats(new Stats(30, 8, 3)).skill(flare).build();
        GameSession session = new GameSession(1,0,0, List.of(aria),
                new CryptBiome(random),
                List.of(EnemyKind.SKELETON, EnemyKind.ASH_RAT),
                new FlatFormula(), new DamagePipeline(random, 0.0));
        Combat combat = session.combat();
        Fighter hero = session.heroes().get(0);
        Fighter skeleton = session.enemies().get(0);
        Fighter rat = session.enemies().get(1);
        Item bomb = session.inventory().items().get(2);
        List<CombatCommand> commands = List.of(
                new Attack(combat, hero, skeleton),
                new UseSkill(combat, hero, flare, rat),
                new Defend(hero),
                new UseItem(session, hero, bomb, skeleton));
        for (CombatCommand command : commands) {
            command.execute().forEach(System.out::println);
        }
        System.out.println(
                hero.shield() + " " + hero.stats().defense() + " " + session.inventory().items().size());
    }
}
