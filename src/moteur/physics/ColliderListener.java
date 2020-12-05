package moteur.physics;

import moteur.core_kernel.Entity;

/**
 * Interface definissant les classes permettant de traiter les collisions
 */
public interface ColliderListener {
    /**
     * methode se déclenchant lorsque deux entity colisionne
     * @param entity_owned
     * @param entity
     */
    public void onCollision(Entity entity_owned, Entity entity);
}
