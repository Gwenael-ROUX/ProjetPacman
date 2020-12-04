package moteur.ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import moteur.core_kernel.GameManager;
import moteur.core_kernel.Map;

public class MenuController implements SceneController{
    private MenuView view;
    private GameManager gameManager;
    private SceneManager sceneManager;

    public MenuController(GameManager gameManager, SceneManager sceneManager) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
    }

    @Override
    public void init(Map map) {
        view = new MenuView(map);

        view.getGameButton1P().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewController gameViewController = new GameViewController();
                gameViewController.init(gameManager.getMap());
                sceneManager.setSceneView(gameViewController);
            }
        });
        view.getGameButton2P().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

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

    @Override
    public Parent getView() {
        return view;
    }
}
