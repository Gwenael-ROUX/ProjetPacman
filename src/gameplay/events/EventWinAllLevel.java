package gameplay.events;

import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import gameplay.scene.GameViewController;
import moteur.sound.SoundManager;

/**
 * Event permettant de changer de level à la fin d'un niveau
 */
public class EventWinAllLevel extends Event {
    private GameViewController controller;

    public EventWinAllLevel(Entity entity, GameViewController controller, int time) {
        super(entity, time);
        this.controller = controller;
    }

    @Override
    public void handle() {
        SoundManager.getInstance().addSound("pacman_beginning.wav", "intro", false, 0.2f, 0L);
        controller.getGameView().getChildren().clear();
        controller.setEndlevel(false);
        controller.getBackMenuWin();
    }
}
