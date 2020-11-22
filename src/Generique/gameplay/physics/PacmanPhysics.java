package Generique.gameplay.physics;

import Generique.gameplay.EntityType;
import Generique.gameplay.events.EventCeriseScore;
import Generique.gameplay.events.EventGommeScore;
import Generique.gameplay.model.PacmanModel;
import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.EventManager;
import Generique.moteur.physics.BoxCollider;
import Generique.moteur.physics.Collider;
import Generique.moteur.physics.PhysicsComponent;

public class PacmanPhysics extends PhysicsComponent {
    private PacmanModel pacmanModel;

    public PacmanPhysics(double speed, Collider collider, PacmanModel pacmanModel) {
        super(speed);
        this.collider = collider;
        this.pacmanModel = pacmanModel;
    }

    @Override
    public void onCollision(Entity entity_owned, Entity entity){
        if(entity.getName().equals(EntityType.WALL.name) || entity.getName().equals(EntityType.GHOST.name)){
            pacmanModel.decrementPV();
            moveBack(entity_owned);
        } else if(entity.getName().equals(EntityType.GOMME.name)){
            EventManager.getEventManager().addEvent(new EventGommeScore(pacmanModel));
        } else if(entity.getName().equals(EntityType.CERISE.name)){
            EventManager.getEventManager().addEvent(new EventCeriseScore(pacmanModel));
        }
    }

    @Override
    public void onExit(Entity entity_owned){
        moveBack(entity_owned);
    }

    private void moveBack(Entity entity_owned){
        entity_owned.setOrientation((entity_owned.getOrientation()+180.0)%360);
        entity_owned.move();
        entity_owned.setOrientation((entity_owned.getOrientation()-180.0)%360);
    }
}
