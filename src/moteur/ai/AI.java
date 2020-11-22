package moteur.ai;

import moteur.controller.ControllerComponent;
import moteur.core_kernel.Entity;

public interface AI extends ControllerComponent {
    public void update(Entity entity);
}
