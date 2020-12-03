package gameplay.events;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.core_kernel.Map;
import moteur.physics.Position;

public class EventEatCherry extends Event {
    private PacmanModel pacmanModel;
    private Map map;

    public EventEatCherry(PacmanModel pacmanModel, Entity entity, Map map) {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
    }

    @Override
    public void handle() {
        Position position = map.getPositionEntity(entity);
        if(position == null) return;

        pacmanModel.addScore(50);
        map.deleteEntity(position, entity);
    }
}
