package moteur.ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import moteur.core_kernel.Map;

public class MenuController implements SceneController{
    private MenuView view;

    public MenuController(){
    }

    @Override
    public void init() {
        view = new MenuView();


        view.getGameButton1P().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewController gameViewController = new GameViewController(1, false);
                SceneManager.getInstance().setSceneView(gameViewController);
            }
        });
        view.getGameButton2P().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewController gameViewController = new GameViewController(1, true);
                SceneManager.getInstance().setSceneView(gameViewController);
            }
        });
        view.getControls().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        view.getHelpButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        view.getQuitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
    }

    @Override
    public void update(Map map) {

    }

    public Parent getView() {
        return view;
    }

    @Override
    public void resetGame() {

    }
}
