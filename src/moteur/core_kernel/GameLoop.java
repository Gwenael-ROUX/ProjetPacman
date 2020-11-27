package moteur.core_kernel;


import gameplay.LevelGenerator;
import gameplay.controller.GhostKeyboardController;
import gameplay.controller.PacmanKeyboardController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import moteur.controller.GeneralKeyboardController;
import moteur.controller.KeyboardController;
import moteur.ui.SceneGame;
import moteur.ui.SceneManager2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


public class GameLoop extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        LevelGenerator levelGenerator = new LevelGenerator(600,600, "/Level/level2.txt");
        GameManager gameManager = new GameManager(levelGenerator.getMap());

        //stage.setResizable(false);
        SceneManager2 sceneManager2 = new SceneManager2(stage, "pacman");
        KeyboardController keyboard1 = (KeyboardController) levelGenerator.getPacman().getControllerComponent();
        KeyboardController keyboard2 = (KeyboardController) levelGenerator.getGhost().getControllerComponent();
        sceneManager2.setRoot(gameManager.getBuildSceneGame().getSceneGame());
        GeneralKeyboardController keyboardController = new GeneralKeyboardController(new ArrayList<>(Arrays.asList(keyboard1, keyboard2)));
        sceneManager2.getStage().getScene().setOnKeyPressed(keyboardController.getEventHandler());

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) * 10e-10;
                Timer.getInstance().setTime(t);

                gameManager.update();
            }
        }.start();

         sceneManager2.show(gameManager.getBuildSceneGame().getSceneGame());
    }
}