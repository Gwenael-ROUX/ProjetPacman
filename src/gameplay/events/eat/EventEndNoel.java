package gameplay.events.eat;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.sound.SoundManager;

/**
 * Event repassant pacnoel dans l'etat pacman
 */
public class EventEndNoel extends Event {
    private PacmanModel pacmanModel;

    public EventEndNoel(PacmanModel pacmanModel, Entity entity, int time) {
        super(entity, time);
        this.pacmanModel = pacmanModel;
    }

    @Override
    public void handle() {
        SoundManager.getInstance().stopAllSound();
        pacmanModel.setNoel(false);
    }
}
