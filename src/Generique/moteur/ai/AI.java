package Generique.moteur.ai;

import Generique.moteur.controller.ControllerComponent;
import Generique.moteur.core_kernel.Entity;

public interface AI extends ControllerComponent {
    public void update(Entity entity);
}
