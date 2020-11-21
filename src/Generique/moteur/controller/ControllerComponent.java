package Generique.moteur.controller;

import Generique.moteur.Component;
import Generique.moteur.core_kernel.Entity;

public interface ControllerComponent extends Component {
    public void update(Entity entity);
}
