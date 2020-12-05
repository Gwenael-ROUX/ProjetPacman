package gameplay.events.animation;

import gameplay.model.GameModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.core_kernel.GameLoop;
import moteur.sound.SoundManager;
import gameplay.scene.MenuController;
import moteur.ui.SceneManager;

public class EventAnimMort extends Event {
    private Entity entity_owned;
    protected EventAnimMort(Entity entity_owned, Entity entity, int time) {
        super(entity, time);
        this.entity_owned = entity_owned;
    }

    @Override
    public void handle() {
        GameModel.getInstance().resetPacMan();
        System.out.println("Game Over!");
        SceneManager.getInstance().setSceneView(new MenuController());
        SoundManager.getInstance().stopAllSound();
        GameLoop.setGameManager(null);
    }
}
