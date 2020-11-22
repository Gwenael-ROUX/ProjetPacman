package moteur.controller;

import moteur.Component;
import moteur.core_kernel.Entity;

public interface ControllerComponent extends Component {
    public void update(Entity entity);
}
