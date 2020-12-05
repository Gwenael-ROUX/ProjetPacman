package gameplay.events;

import gameplay.LevelGenerator;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.ui.GameViewController;

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
