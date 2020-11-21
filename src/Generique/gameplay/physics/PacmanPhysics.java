package Generique.gameplay.physics;

import Generique.moteur.core_kernel.Entity;
import Generique.moteur.physics.BoxCollider;
import Generique.moteur.physics.Collider;
import Generique.moteur.physics.PhysicsComponent;

public class PacmanPhysics extends PhysicsComponent {
    public PacmanPhysics(double speed, Collider collider) {
        super(speed);
        this.collider = collider;
    }

    @Override
    public void onCollision(Entity entity) {

    }
}
