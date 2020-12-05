package moteur.physics;

import moteur.core_kernel.Entity;

/**
 * Interface definisant les classes detectant lorsqu'une entity
 */
public interface ExitListener {
    /**
     * methode se déclenchant lorsqu'une entity sort d'une zone définis
     * @param entity_owned
     */
    public void onExit(Entity entity_owned);
}
