package gameplay.events;

import gameplay.PacmanGame;
import gameplay.model.PacmanModel;
import moteur.core_kernel.*;
import moteur.graphique.AnimationManager;
import moteur.sound.SoundManager;

public class EventPacmanDie extends Event {
    private PacmanModel pacmanModel;
    private Map map;
    private Entity entity_owned;

    public EventPacmanDie(PacmanModel pacmanModel, Entity entity, Entity entity_owned, Map map) {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
        this.entity_owned = entity_owned;
    }

    @Override
    public void handle() {
        entity_owned.getGraphicsComponent().getAnimationManager().setCurrentAnimation("mort");
        SoundManager.getInstance().stopAllSound();
        SoundManager.getInstance().addSound("mort.wav", "mort", false, 0.8f, 400L);
        EventManager.getEventManager().addEvent(new EventPacnoelDie(entity_owned, entity, 200));
    }
}
