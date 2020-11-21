package Generique.gameplay.physics;

import Generique.moteur.core_kernel.Entity;
import Generique.moteur.physics.BoxCollider;
import Generique.moteur.physics.Collider;
import Generique.moteur.physics.PhysicsComponent;

public class WallPhysics extends PhysicsComponent {
    public WallPhysics(double speed, Collider<BoxCollider> collider) {
        super(speed);
        this.collider = collider;
    }

    @Override
    public void onCollision(Entity entity) {

    }
}
