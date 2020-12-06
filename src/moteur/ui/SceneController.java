package moteur.ui;


import javafx.scene.Parent;
import moteur.core_kernel.Map;

public interface SceneController {
    void init();
    void update(Map map);
    Parent getView();
}
