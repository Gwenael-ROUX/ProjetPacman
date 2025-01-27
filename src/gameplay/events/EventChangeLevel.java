package gameplay.events;

import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import gameplay.scene.GameViewController;
import moteur.sound.SoundManager;

/**
 * Event permettant de changer de level à la fin d'un niveau
 */
public class EventChangeLevel extends Event {
    private GameViewController controller;

    public EventChangeLevel(Entity entity, GameViewController controller, int time) {
        super(entity, time);
        this.controller = controller;
    }

    @Override
    public void handle() {
        controller.getGameView().getChildren().clear();
        controller.setNewLevel();
        controller.setEndlevel(false);
        controller.getGameManager().setMap(controller.getLevelGenerator().getMap());
        controller.init();
    }
}
