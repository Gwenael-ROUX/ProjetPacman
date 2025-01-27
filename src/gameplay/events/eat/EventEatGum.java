package gameplay.events.eat;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.core_kernel.Map;
import moteur.physics.Position;
import moteur.sound.SoundManager;

/**
 * Event gerant l'action manger une gomme (sucre d'orge)
 */
public class EventEatGum extends Event {
    private PacmanModel pacmanModel;
    private Map map;

    public EventEatGum(PacmanModel pacmanModel, Entity entity, Map map) {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
    }

    @Override
    public void handle() {
        Position position = map.getPositionEntity(entity);
        if(position == null) return;

        pacmanModel.addScore(10);
        entity.getGraphicsComponent().getCurrentImage().setImage(null);
        map.deleteEntity(position, entity);
        SoundManager.getInstance().addSound("pacman_chomp.wav", "chomp", false, 0.2f, 500L);
    }
}
