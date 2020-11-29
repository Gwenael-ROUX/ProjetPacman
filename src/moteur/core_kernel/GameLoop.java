package moteur.core_kernel;


import moteur.controller.GeneralKeyboardController;
import moteur.controller.KeyboardController;
import moteur.ui.SceneManager2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;


// TODO : TEST Temporary suppress gameplay package to see motor dependencies
public class GameLoop extends Application {

    private static double time;
    private static float timeMultiplicator = 1f;
    private static String title = "";
    private static long startTimeModifMult;
    private static AnimationTimer animationTimer;
    private static GeneralKeyboardController keyboardController;
    private static GameManager gameManager;


    @Override
    public void start(Stage stage) {
        if(gameManager == null) return;
        //stage.setResizable(false);
        SceneManager2 sceneManager2 = new SceneManager2(stage, title);
        sceneManager2.setRoot(gameManager.getBuildSceneGame().getSceneGame());

        if(keyboardController != null)
            sceneManager2.getStage().getScene().setOnKeyPressed(keyboardController.getEventHandler());

        final long startNanoTime = System.nanoTime();

        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                time = (currentNanoTime - startNanoTime) * 10e-10 * timeMultiplicator;
                Timer.getInstance().setTime(time);

                gameManager.update();
            }
        };
        animationTimer.start();

         sceneManager2.show(gameManager.getBuildSceneGame().getSceneGame());
    }

    public static void startGame(){
        launch();
    }

    public static void setTimeAnimation(float timeModification){
        timeMultiplicator = timeModification;
    }

    public static void setTitle(String title){
        GameLoop.title = title;
    }

    public static void stopGame(){
        animationTimer.stop();
    }

    public static void setGameManager(GameManager gameManager){
        GameLoop.gameManager = gameManager;
    }

    public static void setKeyboardController(GeneralKeyboardController keyboardController){
        GameLoop.keyboardController = keyboardController;
    }
}