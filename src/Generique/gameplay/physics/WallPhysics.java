package Generique.gameplay.physics;

import Generique.moteur.physics.Collider;
import Generique.moteur.physics.PhysicsComponent;

public class WallPhysics extends PhysicsComponent {
    public WallPhysics(Collider collider) {
        super(0.0);
        this.collider = collider;
    }
}
