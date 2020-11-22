package gameplay.physics;

import moteur.physics.Collider;
import moteur.physics.PhysicsComponent;

public class GommePhysics extends PhysicsComponent {
    public GommePhysics(Collider collider) {
        super(0.0);
        this.collider = collider;
    }
}
