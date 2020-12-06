package gameplay.scene;

import javafx.scene.Parent;
import moteur.core_kernel.Map;
import moteur.ui.SceneController;
import moteur.ui.ViewFX;

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

    public Parent getView() {
        return view;
    }
}
