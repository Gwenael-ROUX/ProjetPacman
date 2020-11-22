package gameplay.physics;

import moteur.physics.Collider;
import moteur.physics.PhysicsComponent;

public class WallPhysics extends PhysicsComponent {
    public WallPhysics(Collider collider) {
        super(0.0);
        this.collider = collider;
    }
}
