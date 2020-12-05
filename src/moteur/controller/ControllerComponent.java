package moteur.controller;

import moteur.core_kernel.Component;
import moteur.core_kernel.Entity;

/**
 * Interface du controlleur, permet les mouvements d'un perso par Input clavier
 */
public interface ControllerComponent extends Component {
    public void update(Entity entity);
}
