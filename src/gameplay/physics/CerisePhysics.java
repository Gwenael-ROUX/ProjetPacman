package gameplay.physics;

import moteur.physics.Collider;
import moteur.physics.PhysicsComponent;

public class CerisePhysics extends PhysicsComponent {
    public CerisePhysics(Collider collider) {
        super(0.0);
        this.collider = collider;
    }
}
