package gameplay.events;

import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.core_kernel.GameLoop;
import moteur.core_kernel.Map;
import moteur.physics.Position;

public class EventPacmanDie extends Event {

    private PacmanModel pacmanModel;
    private Map map;

    public EventPacmanDie(PacmanModel pacmanModel, Map map) {
        this.pacmanModel = pacmanModel;
        this.map = map;
    }

    @Override
    public void handle() {
        System.out.println("Game over !");
        GameLoop.SetTimeMultiplicator(0.1f);
    }
}
