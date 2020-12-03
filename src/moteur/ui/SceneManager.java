package moteur.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import moteur.core_kernel.Map;

public class SceneManager {
    private Stage stage;
    private SceneView sceneView;

    public SceneManager(Stage stage, String title) {
        this.stage = stage;
        this.stage.setTitle(title);
        this.stage.sizeToScene();
        this.stage.centerOnScreen();
    }

    public void show() {
        stage.show();
    }

    public void setRoot(Parent root) {
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(root);
            stage.setScene(scene);
        }
        scene.setRoot(root);
    }

    public void update(Map map) {
        sceneView.update(map);
    }

    public void setSceneView(SceneView sceneView) {
        this.sceneView = sceneView;
        setRoot(sceneView);
    }

    public Stage getStage() {
        return stage;
    }

}
