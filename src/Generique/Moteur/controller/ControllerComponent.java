package Generique.Moteur.controller;

import Generique.Moteur.Component;
import Generique.Moteur.core_kernel.Entity;

public interface ControllerComponent extends Component {
    public void update(Entity entity);
}
