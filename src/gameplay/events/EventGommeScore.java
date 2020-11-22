package gameplay.events;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Event;

public class EventGommeScore extends Event {
    private PacmanModel pacmanModel;

    public EventGommeScore(PacmanModel pacmanModel){
        this.pacmanModel = pacmanModel;
    }

    @Override
    public void handle() {
        pacmanModel.addScore(10);
    }
}
