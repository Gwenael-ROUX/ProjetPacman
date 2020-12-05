package moteur.core_kernel;


import javafx.scene.Parent;
import moteur.controller.GeneralKeyboardController;
import moteur.ui.MenuController;
import moteur.ui.SceneController;
import moteur.ui.SceneManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;


public class GameLoop extends Application {

    private static double time;
    private static float timeMultiplicator = 1f;
    private static String title = "";
    private static long startTimeModifMult;
    private static AnimationTimer animationTimer;
    private static GameManager gameManager;
    private static SceneController sceneController;


    @Override
    public void start(Stage stage) throws Exception {
        SceneManager.getInstance(stage);
        SceneManager.getInstance().setSceneView(sceneController);
        SceneManager.getInstance().setTitle(title);

        final long startNanoTime = System.nanoTime();

        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                time = (currentNanoTime - startNanoTime) * 10e-10 * timeMultiplicator;
                Timer.getInstance().setTime(time);
                if (gameManager != null)
                    gameManager.update();
            }
        };
        animationTimer.start();

         SceneManager.getInstance().show();
    }

    public static void startGame(){
        launch();
    }

    public static void setAnimationTimer(AnimationTimer animationTimer) {
        GameLoop.animationTimer = animationTimer;
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

    public static void startAnimationTiemer() {
        animationTimer.start();
    }

    public static void setGameManager(GameManager gameManager){
        GameLoop.gameManager = gameManager;
    }

    public static void setSceneController(SceneController sceneController) {
        GameLoop.sceneController = sceneController;
    }
}