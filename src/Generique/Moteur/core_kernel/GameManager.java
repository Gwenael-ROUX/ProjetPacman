package Generique.Moteur.core_kernel;

import Generique.Moteur.physics.ColliderListener;
import java.util.ArrayList;
import java.util.List;


public class GameManager {
    private List<ColliderListener> colliderListeners;
    private Map map;
    private EventManager eventManager;


    public GameManager(Entity[][] entities){
        colliderListeners = new ArrayList<>();
        map = new Map(entities);
        eventManager = EventManager.getEventManager();
    }


    public void update(){
        // Events

        // Loop on entities

        // Collision listener
    }
}
