package gameplay.events;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.sound.SoundManager;

public class EventEatGhost extends Event {
    private PacmanModel pacmanModel;

    public EventEatGhost(PacmanModel pacmanModel, Entity entity) {
        super(entity);
        this.pacmanModel = pacmanModel;
    }

    @Override
    public void handle() {
        pacmanModel.addScore(150);
        SoundManager.getInstance().addSound("pacman_eatghost.wav", "ghost", false, 0.2f, 0L);
    }
}
