package moteurs.controllers.ai;

import moteurs.physics.Displacement;
import moteurs.controllers.Controller;

public interface AI extends Controller {
    Displacement move();
}
