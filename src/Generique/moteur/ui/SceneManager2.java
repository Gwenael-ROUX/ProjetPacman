package Generique.moteur.ui;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SceneManager2 {
    private Stage stage;

    public SceneManager2(Stage stage, String title) {
        this.stage = stage;
        this.stage.setTitle(title);
        this.stage.sizeToScene();
        this.stage.centerOnScreen();
    }

    public void show(Parent root) {
        setRoot(root);
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

    public Stage getStage() {
        return stage;
    }
}
