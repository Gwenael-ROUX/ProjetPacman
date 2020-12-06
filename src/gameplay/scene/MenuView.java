package gameplay.scene;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import moteur.ui.BoutonUI;
import moteur.ui.SceneManager;
import moteur.ui.ViewFX;

public class MenuView extends ViewFX {
    private BoutonUI gameButton1P;
    private BoutonUI gameButton2P;
    private BoutonUI controls;
    private BoutonUI helpButton;
    private BoutonUI quitButton;

    public MenuView(double height, double width) {
        setHeightScene(height);
        setWidthScene(width);
        init();
    }

    @Override
    public void init() {
        setBackgroundScene("/Image/Menu/PacNoel_menu.png");

        gameButton1P = new BoutonUI("Start Game -1P",getPrefWidth() * 0.27,getPrefHeight() * 0.3);
        gameButton1P.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),30);
        gameButton1P.setColor(Color.WHITE);

        gameButton2P = new BoutonUI("Start Game -2P",getPrefWidth() * 0.27,getPrefHeight() * 0.4);
        gameButton2P.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),30);
        gameButton2P.setColor(Color.WHITE);

        controls = new BoutonUI("Controle",getPrefWidth() * 0.27,getPrefHeight() *0.5);
        controls.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),30);
        controls.setColor(Color.WHITE);

        helpButton= new BoutonUI("Aide",getPrefWidth() * 0.27,getPrefHeight() * 0.6);
        helpButton.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),30);
        helpButton.setColor(Color.WHITE);

        quitButton= new BoutonUI("Quitter",getPrefWidth() * 0.27,getPrefHeight() * 0.7);
        quitButton.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),30);
        quitButton.setColor(Color.WHITE);

        gameButton1P.setAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewController gameViewController = new GameViewController(1, false);
                SceneManager.getInstance().setSceneView(gameViewController);
            }
        });
        gameButton2P.setAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewController gameViewController = new GameViewController(1, true);
                SceneManager.getInstance().setSceneView(gameViewController);
            }
        });
        controls.setAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.getInstance().setRoot(new ControlesView(getHeightScene(), getWidthScene()));
            }
        });
        helpButton.setAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.getInstance().setRoot(new OptionsView(getHeightScene(), getWidthScene()));
            }
        });
        quitButton.setAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        addToScene(gameButton1P.getButton());
        addToScene(gameButton2P.getButton());
        addToScene(controls.getButton());
        addToScene(helpButton.getButton());
        addToScene(quitButton.getButton());
    }
}
