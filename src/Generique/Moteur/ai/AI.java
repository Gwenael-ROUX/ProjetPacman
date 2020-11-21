package Generique.Moteur.ai;

import Generique.Moteur.controller.ControllerComponent;
import Generique.Moteur.core_kernel.Entity;

public interface AI extends ControllerComponent {
    public void update(Entity entity);
}
