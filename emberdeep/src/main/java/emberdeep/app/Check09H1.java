package emberdeep.app;

import emberdeep.engine.event.EventBus;
import emberdeep.engine.event.GameEvent.DamageDodged;
import emberdeep.engine.event.GameEvent.FighterDefeated;
import emberdeep.engine.event.GameListener;

/**
 * Check09H1
 */
public class Check09H1 {

    public static void main(String[] args) {
        EventBus bus = new EventBus();
        GameListener printer = e -> System.out.println("oído: " + e);
        bus.subscribe(printer);
        bus.publish(new DamageDodged("Esqueleto", "Aria", true));
        bus.unsubscribe(printer);
        bus.publish(new FighterDefeated("Esqueleto", false));
        System.out.println("fin");
    }
}
