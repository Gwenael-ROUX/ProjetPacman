package gameplay.events;

import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.sound.SoundManager;

public class EventEatGhost extends Event {

    public EventEatGhost(Entity entity) {
        super(entity);
    }

    @Override
    public void handle() {
        //map.deleteEntity(position, entity);
        //entity.getGraphicsComponent().getCurrentImage().setImage(null);
        SoundManager.getInstance().addSound("pacman_chomp.wav", "chomp", false, 0.2f, 500L);
    }
}
