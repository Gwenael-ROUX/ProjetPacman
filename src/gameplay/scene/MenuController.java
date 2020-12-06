package gameplay.scene;

import moteur.core_kernel.Map;
import moteur.ui.SceneController;
import moteur.ui.ViewFX;

/**
 * Classe permettant de gerer les vues du menu
 */
public class MenuController implements SceneController {
    private ViewFX view;

    public MenuController(){
    }

    @Override
    public void init() {
        view = new MenuView(500,500);
    }

    @Override
    public void update(Map map) {

    }

    public ViewFX getView() {
        return view;
    }
}
