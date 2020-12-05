package moteur.ui;

import gameplay.LevelGenerator;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import moteur.core_kernel.GameManager;
import moteur.core_kernel.Map;

public interface SceneController {
    void init();
    void update(Map map);
    Parent getView();
}
