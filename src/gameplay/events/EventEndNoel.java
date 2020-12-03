package gameplay.events;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.sound.SoundManager;

public class EventEndNoel extends Event {
    private PacmanModel pacmanModel;

    public EventEndNoel(PacmanModel pacmanModel, Entity entity, int time) {
        super(entity, time);
        this.pacmanModel = pacmanModel;
    }

    @Override
    public void handle() {
        pacmanModel.setNoel(false);
        //SoundManager.getInstance().stopASound("isNoel");
        //entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(entity.getOrientation().toString());
    }
}
