package gameplay.events.animation;

import gameplay.Score;
import gameplay.model.PacmanModel;
import gameplay.scene.GameView;
import gameplay.scene.GameViewController;
import moteur.core_kernel.*;
import moteur.sound.SoundManager;

public class EventPacmanDie extends Event {
    private PacmanModel pacmanModel;
    private Map map;
    private Entity entity_owned;
    private final Score score = GameViewController.getScore();

    public EventPacmanDie(PacmanModel pacmanModel, Entity entity, Entity entity_owned, Map map) {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
        this.entity_owned = entity_owned;
    }

    @Override
    public void handle() {
        if(Integer.parseInt(score.getScorefile() ) < pacmanModel.getScore()){
            GameViewController.setSessionBestScore(pacmanModel.getScore());
            score.setScorefile(pacmanModel.getScore()+"");
        }
        entity_owned.getGraphicsComponent().getAnimationManager().setCurrentAnimation("mort");
        SoundManager.getInstance().stopAllSound();
        SoundManager.getInstance().addSound("mort.wav", "mort", false, 0.2f, 400L);
        EventManager.getEventManager().addEvent(new EventPacnoelDie(entity_owned, entity, 200));
    }
}
