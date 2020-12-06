package moteur.ui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import moteur.core_kernel.Map;
import moteur.sound.SoundManager;

public class SceneManager {
    private Stage stage;
    private SceneController sceneController;
    private static SceneManager instance;

    private SceneManager(Stage stage) {
        this.stage = stage;
        this.stage.sizeToScene();
        this.stage.centerOnScreen();
        this.stage.setResizable(false);
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                SoundManager.getInstance().stopAllSound();
            }
        });
    }

    public static SceneManager getInstance(Stage stage) {
        if (instance == null) {
            instance = new SceneManager(stage);
        }
        return instance;
    }

    public static SceneManager getInstance() {
        return instance;
    }

    public void setTitle(String title) {
        stage.setTitle(title);
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
        sceneController.update(map);
    }

    public void setSceneView(SceneController sceneController) {
        this.sceneController = sceneController;
        this.sceneController.init();
        setRoot(this.sceneController.getView());
    }

    public Stage getStage() {
        return stage;
    }

    public void exit(){
        Platform.exit();
    }
}
