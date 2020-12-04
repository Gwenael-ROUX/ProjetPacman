package gameplay.events;

import gameplay.PacmanGame;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.graphique.AnimationManager;

public class EventAnimMort extends Event {
    private Entity entity_owned;
    protected EventAnimMort(Entity entity_owned, Entity entity, int time) {
        super(entity, time);
        this.entity_owned = entity_owned;
    }

    @Override
    public void handle() {
        System.out.println("Game Over!");
        PacmanGame.getGame().stopGame();
    }
}
