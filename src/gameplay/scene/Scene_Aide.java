package gameplay.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import moteur.ui.SceneManager;

public class Scene_Aide extends StackPane {

    Scene_Aide(SceneManager sceneManager2){

        Pane stackPane = new Pane();

        Button button = new Button("retour");

        getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent root = new Scene_Menu(sceneManager2);
                sceneManager2.getStage().setScene(new Scene(root,400,400));
            }
        });
    }

}
