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
import emberdeep.engine.combat.command.Defend;
import emberdeep.engine.combat.command.UseSkill;
import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.combat.damage.FlatFormula;
import emberdeep.engine.combat.defense.DamagePipeline;
import emberdeep.engine.event.EventBus;
import emberdeep.engine.session.GameSession;
import emberdeep.engine.world.biome.CryptBiome;
import emberdeep.ui.listener.AchievementTracker;
import emberdeep.ui.listener.CombatLog;
import emberdeep.ui.listener.HudListener;

public class Check09H3 {
    public static void main(String[] args) {
        Random random = new Random(42);
        Hero aria = new HeroBuilder().name("Aria")
                .stats(new Stats(7, 8, 0))
                .skill(new Skill("Golpe poderoso", 12, DamageType.PHYSICAL)).build();
        GameSession session = new GameSession(1, 0, 0, List.of(aria),
                new CryptBiome(random), List.of(EnemyKind.SKELETON),
                new FlatFormula(), new DamagePipeline(random, 0.0));
        EventBus events = session.events();
        events.subscribe(new CombatLog(System.out));
        events.subscribe(new HudListener(System.out));
        events.subscribe(new AchievementTracker(events));
        Combat combat = session.combat();
        Fighter hero = session.heroes().get(0);
        Fighter skeleton = session.enemies().get(0);
        for (int round = 1; round <= 5; round++) {
            combat.perform(new Defend(combat, hero));
            combat.enemyPhase();
        }
        combat.perform(new UseSkill(combat, hero,
                hero.skills().get(0), skeleton));
        combat.enemyPhase();
        combat.perform(new Attack(combat, hero, skeleton));
    }
}
