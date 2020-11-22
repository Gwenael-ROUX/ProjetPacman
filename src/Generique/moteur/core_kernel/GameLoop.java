package Generique.moteur.core_kernel;


import Generique.gameplay.LevelGenerator;
import Generique.gameplay.controller.PacmanKeyboardController;
import Generique.moteur.controller.KeyboardController;
import Generique.moteur.graphique.AnimationManager;
import Generique.moteur.ui.SceneGame;
import Generique.moteur.ui.SceneManager;
import Generique.moteur.ui.SceneManager2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameLoop extends Application {
    GameManager gameManager;
    SceneManager sceneManager;

//    public GameLoop(GameManager gameManager) {
//        this.gameManager = gameManager;
//    }

    @Override
    public void start(Stage stage) {
        LevelGenerator levelGenerator = new LevelGenerator(512,512, "/Level/level1.txt");
        GameManager gameManager = new GameManager(levelGenerator.getMapRepresentation().getMap());

        stage.setResizable(false);
        SceneManager2 sceneManager2 = new SceneManager2(stage, "pacman");
        PacmanKeyboardController keyboard = (PacmanKeyboardController) levelGenerator.getPacman().getControllerComponent();
        gameManager.getBuildSceneGame().getSceneGame().setOnKeyPressed(keyboard.getEventHandler());

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 10e+9;//1000000000.0;
                Timer.getInstance().setTime(t);

                gameManager.update();
            }
        }.start();

         sceneManager2.show(gameManager.getBuildSceneGame().getSceneGame());
    }
}