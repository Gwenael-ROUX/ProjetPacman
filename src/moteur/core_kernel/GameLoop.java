package moteur.core_kernel;


import moteur.controller.GeneralKeyboardController;
import moteur.ui.MenuController;
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
    private static GeneralKeyboardController keyboardController;
    private static GameManager gameManager;


    @Override
    public void start(Stage stage) throws Exception {
        if(gameManager == null) return;
        //stage.setResizable(false);
        SceneManager sceneManager = new SceneManager(stage, title);
        MenuController menuController = new MenuController(gameManager, sceneManager);
        menuController.init(gameManager.getMap());
        sceneManager.setSceneView(menuController);

        if(keyboardController != null)
            sceneManager.getStage().getScene().setOnKeyPressed(keyboardController.getEventHandler());

        //gameManager.setSceneManager(sceneManager);

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

         sceneManager.show();
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