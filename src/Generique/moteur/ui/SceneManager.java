package Generique.moteur.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class SceneManager {

    public static Stage stage;

    public static void setStage(Stage stage){
        SceneManager.stage = stage;
    }

    public static void switchScene( Scene scene){
        stage.setScene(scene);
    }
}
