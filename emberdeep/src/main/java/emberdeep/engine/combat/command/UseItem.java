package emberdeep.engine.combat.command;

import java.util.ArrayList;
import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.damage.Damage;
import emberdeep.engine.combat.damage.DamageType;
import emberdeep.engine.event.GameEvent;
import emberdeep.engine.item.Inventory;
import emberdeep.engine.item.Item;
import emberdeep.engine.session.GameSession;
import emberdeep.engine.stats.StatSource;

/**
 * UseItem
 */
public class UseItem implements CombatCommand {
    private Inventory inventoryBefore;
    private int hpBefore;
    private int shieldBefore;
    private StatSource statsBefore;
    private final GameSession session;
    private final Fighter target;
    private final Fighter user;
    private final Item item;
    private final Combat combat;

    public UseItem(GameSession session, Fighter user, Item item, Fighter target) {
        this.session = session;
        this.target = target;
        this.user = user;
        this.item = item;
        this.combat = session.combat();
    }

    @Override
    public void execute() {
        inventoryBefore = session.inventory();
        hpBefore = target.hp();
        shieldBefore = target.shield();
        statsBefore = target.stats();
        session.replaceInventory(inventoryBefore.without(item));

        List<String> messages = new ArrayList<>();

        switch (item.kind()) {
            case HEALING -> combat.publish(new GameEvent.ItemUsed(
                        user.name(), target.name(), item.name(),
                        target.hp(), target.stats().maxHp()));
            case BOMB -> combat.deal(user, target, new Damage(item.power(), DamageType.FIRE));
        };
    }

    @Override
    public void undo() {
        session.replaceInventory(inventoryBefore);
        target.restore(hpBefore, shieldBefore, statsBefore);
    }

    
}
