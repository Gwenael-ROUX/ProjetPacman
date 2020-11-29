package gameplay;

import javafx.stage.Stage;
import moteur.controller.GeneralKeyboardController;
import moteur.controller.KeyboardController;
import moteur.core_kernel.GameLoop;
import moteur.core_kernel.GameManager;
import moteur.ui.SceneManager2;

import java.util.ArrayList;
import java.util.Arrays;

public class PacmanGame {
    public static void main(String[] args) {
        LevelGenerator levelGenerator = new LevelGenerator(600,600, "/Level/level2.txt");
        GameManager gameManager = new GameManager(levelGenerator.getMap());

        Stage stage = new Stage();
        stage.setResizable(false);
        SceneManager2 sceneManager2 = new SceneManager2(stage, "pacman");
        KeyboardController keyboard1 = (KeyboardController) levelGenerator.getPacman().getControllerComponent();
        KeyboardController keyboard2 = (KeyboardController) levelGenerator.getGhost().getControllerComponent();
        sceneManager2.setRoot(gameManager.getBuildSceneGame().getSceneGame());
        GeneralKeyboardController keyboardController = new GeneralKeyboardController(new ArrayList<>(Arrays.asList(keyboard1, keyboard2)));
        sceneManager2.getStage().getScene().setOnKeyPressed(keyboardController.getEventHandler());

        GameLoop gameLoop = new GameLoop(gameManager, sceneManager2);
        gameLoop.start(sceneManager2.getStage());
    }
    public void init() {
        LevelGenerator levelGenerator = new LevelGenerator(512,512,"Level/level1");
        GameManager gameManager = new GameManager(levelGenerator.getMap());
    }
}
