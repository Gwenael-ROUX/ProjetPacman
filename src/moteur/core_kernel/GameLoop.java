package moteur.core_kernel;


import gameplay.LevelGenerator;
import gameplay.controller.PacmanKeyboardController;
import moteur.ui.SceneManager2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameLoop extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        LevelGenerator levelGenerator = new LevelGenerator(600,600, "/Level/level3.txt");
        GameManager gameManager = new GameManager(levelGenerator.getMap());

        stage.setResizable(false);
        SceneManager2 sceneManager2 = new SceneManager2(stage, "pacman");
        PacmanKeyboardController keyboard = (PacmanKeyboardController) levelGenerator.getPacman().getControllerComponent();
        sceneManager2.setRoot(gameManager.getBuildSceneGame().getSceneGame());
        sceneManager2.getStage().getScene().setOnKeyPressed(keyboard.getEventHandler());

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