package gameplay.physics;

import gameplay.EntityType;
import gameplay.events.EventCeriseScore;
import gameplay.events.EventGommeScore;
import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.EventManager;
import moteur.physics.Collider;
import moteur.physics.PhysicsComponent;

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
            moveBack(entity_owned);
            if(entity.getName().equals(EntityType.GHOST.name))
                pacmanModel.decrementPV();
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
        if(entity_owned.getOrientation() == null) return;
        entity_owned.setOrientation((entity_owned.getOrientation()+180.0)%360);
        entity_owned.getPhysicsComponent().update(entity_owned);
        entity_owned.setOrientation((entity_owned.getOrientation()-180.0)%360);
    }
}