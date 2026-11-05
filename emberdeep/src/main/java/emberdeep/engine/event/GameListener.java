package emberdeep.engine.event;

/**
 * GameListener
 */
@FunctionalInterface 
public interface GameListener {

    void onEvent(GameEvent event);
}
