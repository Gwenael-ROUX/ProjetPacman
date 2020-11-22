package Generique.gameplay.physics;

import Generique.moteur.physics.Collider;
import Generique.moteur.physics.PhysicsComponent;

public class GommePhysics extends PhysicsComponent {
    public GommePhysics(Collider collider) {
        super(0.0);
        this.collider = collider;
    }
}
