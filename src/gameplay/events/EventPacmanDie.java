package gameplay.events;

import gameplay.PacmanGame;
import gameplay.model.PacmanModel;
import moteur.core_kernel.Event;
import moteur.core_kernel.GameLoop;
import moteur.core_kernel.Map;

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
        PacmanGame.getGame().stopGame();
    }
}
