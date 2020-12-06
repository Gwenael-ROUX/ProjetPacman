package moteur.ui;


import javafx.scene.Parent;
import moteur.core_kernel.Map;

public interface SceneController {
    /**
     * Initialise la scene
     */
    void init();

    /**
     * Fonction appelee a chaque boucle du jeu. Met a jour la scene
     * @param map map courante a mettre a jour
     */
    void update(Map map);
    Parent getView();
}
