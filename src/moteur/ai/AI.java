package moteur.ai;

import moteur.controller.ControllerComponent;
import moteur.core_kernel.Entity;

/**
 * Interface des IA, valide pour tout NPC
 */
public interface AI extends ControllerComponent {
    public void update(Entity entity);
}
