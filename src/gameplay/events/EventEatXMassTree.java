package gameplay.events;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.core_kernel.EventManager;
import moteur.core_kernel.Map;
import moteur.physics.Position;
import moteur.sound.SoundManager;

public class EventEatXMassTree extends Event {
    private PacmanModel pacmanModel;
    private Entity entityowned;
    private Map map;

    public EventEatXMassTree(PacmanModel pacmanModel, Entity entity, Entity entityowned, Map map) {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
        this.entityowned = entityowned;
    }

    @Override
    public void handle() {

        Position position = map.getPositionEntity(entity);
        if(position == null) return;

        map.deleteEntity(position, entity);
        entity.getGraphicsComponent().getCurrentImage().setImage(null);
        pacmanModel.setNoel(true);
        //SoundManager.getInstance().addSound("isNoel.wav", "isNoel", false, 0.8f, 0L);
        EventManager.getEventManager().addEvent(new EventEndNoel(pacmanModel, entityowned, 660));
    }
}
