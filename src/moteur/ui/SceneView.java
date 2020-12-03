package moteur.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import moteur.core_kernel.Map;

public abstract class SceneView extends Region {

    public abstract void init(Map map, SceneManager sceneManager);
    public abstract void update(Map map);
}
