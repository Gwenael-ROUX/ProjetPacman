package gameplay.events;

import gameplay.PacmanGame;
import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.core_kernel.GameLoop;
import moteur.core_kernel.Map;

public class EventPacmanDie extends Event {
    private PacmanModel pacmanModel;
    private Map map;
    private Entity entity_owned;

    public EventPacmanDie(PacmanModel pacmanModel, Entity entity, Entity entity_owned, Map map) {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
        this.entity_owned = entity_owned;
    }

    @Override
    public void handle() {
        entity_owned.getGraphicsComponent().getAnimationManager().setCurrentAnimation("mort");
        System.out.println("Game over !");
        PacmanGame.getGame().stopGame();
    }
}
