package moteur.physics;

import moteur.core_kernel.Entity;

public interface ColliderListener {
    public void onCollision(Entity entity_owned, Entity entity);
}
