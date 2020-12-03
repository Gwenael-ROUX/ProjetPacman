package gameplay.scene;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import moteur.ui.SceneManager;

public class Scene_Main  extends Application {

    // Si ça marche pas --module-path C:\Users\eddy-\Desktop\javafx-sdk-11.0.2\lib --add-modules=javafx.controls,javafx.fxml
    @Override
    public void start(Stage stage) throws Exception {

        SceneManager sceneManager2 = new SceneManager(stage, "Pacman");
        Parent root = new Scene_Menu(sceneManager2);
        stage.setResizable(false);
        stage.setScene((new Scene(root,600,600)));
        stage.show();


    }

    public static void main(String[] args){
        launch(args);
    }
}