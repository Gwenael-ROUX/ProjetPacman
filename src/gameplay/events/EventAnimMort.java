package gameplay.events;

import gameplay.PacmanGame;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;

public class EventAnimMort extends Event {
    protected EventAnimMort(Entity entity, int time) {
        super(entity, time);
    }

    @Override
    public void handle() {
        PacmanGame.getGame().stopGame();
    }
}
