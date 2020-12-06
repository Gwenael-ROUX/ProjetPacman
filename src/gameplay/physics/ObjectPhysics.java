package gameplay.physics;

import moteur.physics.Collider;
import moteur.physics.PhysicsComponent;

/**
 * classe des objets physiquement present dans le jeu
 */
public class ObjectPhysics extends PhysicsComponent {
    public ObjectPhysics(Collider collider) {
        super(0.0);
        this.collider = collider;
    }
}
