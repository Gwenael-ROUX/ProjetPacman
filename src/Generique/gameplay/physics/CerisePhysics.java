package Generique.gameplay.physics;

import Generique.moteur.physics.Collider;
import Generique.moteur.physics.PhysicsComponent;

public class CerisePhysics extends PhysicsComponent {
    public CerisePhysics(Collider collider) {
        super(0.0);
        this.collider = collider;
    }
}
