package gameplay.events;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.core_kernel.Map;
import moteur.physics.Position;
import moteur.sound.SoundManager;

public class EventEatGum extends Event {
    private PacmanModel pacmanModel;
    private Entity entity;
    private Map map;

    public EventEatGum(PacmanModel pacmanModel, Entity entity, Map map) {
        this.pacmanModel = pacmanModel;
        this.entity = entity;
        this.map = map;
    }

    @Override
    public void handle() {
        Position position = map.getPositionEntity(entity);
        if(position == null) return;

        pacmanModel.addScore(10);
        map.deleteEntity(position, entity);
        entity.getGraphicsComponent().getCurrentImage().setImage(null);
        SoundManager.getInstance().addSound("pacman_chomp.wav", "chomp", false, 0.2f);
    }
}