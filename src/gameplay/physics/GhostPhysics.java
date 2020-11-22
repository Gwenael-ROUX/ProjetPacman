package gameplay.physics;

import gameplay.EntityType;
import moteur.core_kernel.Entity;
import moteur.physics.Collider;
import moteur.physics.PhysicsComponent;

public class GhostPhysics extends PhysicsComponent {
    public GhostPhysics(double speed, Collider collider) {
        super(speed);
        this.collider = collider;
    }

    @Override
    public void onCollision(Entity entity_owned, Entity entity){
        if (entity_owned.getOrientation() == null)
            return;
        if(entity.getName().equals(EntityType.WALL.name) || entity.getName().equals(EntityType.GHOST.name) || entity.getName().equals(EntityType.PACMAN.name)){
            moveBack(entity_owned);
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
