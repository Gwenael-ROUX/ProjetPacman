package moteur.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import moteur.core_kernel.Map;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private Stage stage;
    private SceneController sceneController;

    public SceneManager(Stage stage, String title) {
        this.stage = stage;
        this.stage.setTitle(title);
        this.stage.sizeToScene();
        this.stage.centerOnScreen();
    }

    public void show(GameView sceneGame) {
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
        sceneController.update(map);
    }

    public void setSceneView(SceneController sceneController) {
        this.sceneController = sceneController;
        setRoot(sceneController.getView());
    }

    public Stage getStage() {
        return stage;
    }
}
