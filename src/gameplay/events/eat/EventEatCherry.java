package gameplay.events.eat;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.core_kernel.Map;
import moteur.physics.Position;
import moteur.sound.SoundManager;

/**
 * Event gerant l'action manger un cadeau (cadeau)
 */
public class EventEatCherry extends Event {
    private PacmanModel pacmanModel;
    private Map map;
    private final int[] scoretoadd = new int[4];

    public EventEatCherry(PacmanModel pacmanModel, Entity entity, Map map) {
        super(entity);
        this.pacmanModel = pacmanModel;
        this.map = map;
        scoretoadd[0] = 1000;
        scoretoadd[1] = 2000;
        scoretoadd[2] = 3000;
        scoretoadd[3] = 4000;
    }

    @Override
    public void handle() {
        Position position = map.getPositionEntity(entity);
        if(position == null) return;
        int temp = (int) (Math.random() * 4);
        pacmanModel.addScore(scoretoadd[temp]);
        map.deleteEntity(position, entity);
        entity.getGraphicsComponent().getCurrentImage().setImage(null);
        SoundManager.getInstance().addSound("pacman_eatfruit.wav", "gift", false, 0.2f, 0L);
    }
}
