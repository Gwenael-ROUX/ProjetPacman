package Generique.gameplay.physics;

import Generique.gameplay.EntityType;
import Generique.moteur.core_kernel.Entity;
import Generique.moteur.physics.BoxCollider;
import Generique.moteur.physics.Collider;
import Generique.moteur.physics.PhysicsComponent;

public class GhostPhysics extends PhysicsComponent {
    public GhostPhysics(double speed, Collider collider) {
        super(speed);
        this.collider = collider;
    }

    @Override
    public void onCollision(Entity entity_owned, Entity entity){
        if(entity.getName().equals(EntityType.WALL.name) || entity.getName().equals(EntityType.GHOST.name) || entity.getName().equals(EntityType.PACMAN.name)){
            moveBack(entity_owned);
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
