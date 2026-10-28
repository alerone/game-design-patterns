package emberdeep.engine.combat.command;

import java.util.ArrayList;
import java.util.List;

import emberdeep.engine.character.Fighter;
import emberdeep.engine.combat.Combat;
import emberdeep.engine.combat.damage.Damage;
import emberdeep.engine.combat.damage.DamageType;
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

    public UseItem(GameSession session, Fighter target, Item item, Fighter user, Combat combat) {
        this.session = session;
        this.target = target;
        this.user = user;
        this.item = item;
        this.combat = combat;
    }

    @Override
    public List<String> execute() {
        inventoryBefore = session.getInventory();
        hpBefore = target.getHp();
        shieldBefore = target.getShield();
        statsBefore = target.getStats();
        session.replaceInventory(inventoryBefore.without(item));

        List<String> messages = new ArrayList<>();

        String itemUseMessage = user.getName() + " usa " + item.name()
                    + " sobre " + target.getName();

        switch (item.kind()) {
            case HEALING -> messages.add(itemUseMessage + ": recupera " 
                    + target.heal(item.power()) + " de vida.");
            case BOMB -> messages.add(itemUseMessage + ": inflingiendo " 
                    + combat.deal(target, new Damage(item.power(), DamageType.FIRE))
                    + " de daño de FUEGO.");
        };
        return messages;
    }

    @Override
    public void undo() {
        session.replaceInventory(inventoryBefore);
        target.restore(hpBefore, shieldBefore, statsBefore);
    }

    
}
