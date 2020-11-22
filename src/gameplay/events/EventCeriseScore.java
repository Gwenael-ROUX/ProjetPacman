package gameplay.events;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Event;

public class EventCeriseScore extends Event {
    private PacmanModel pacmanModel;

    public EventCeriseScore(PacmanModel pacmanModel){
        this.pacmanModel = pacmanModel;
    }

    @Override
    public void handle() {
        pacmanModel.addScore(50);
    }
}
