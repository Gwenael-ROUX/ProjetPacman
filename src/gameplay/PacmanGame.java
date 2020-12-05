package gameplay;

import javafx.animation.AnimationTimer;
import moteur.controller.GeneralKeyboardController;
import moteur.controller.KeyboardController;
import moteur.core_kernel.Entity;
import moteur.core_kernel.GameLoop;
import moteur.core_kernel.GameManager;
import moteur.core_kernel.Timer;
import moteur.physics.Position;
import moteur.ui.SceneManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PacmanGame {

    private static int nbLevel;
    private static int currentLevel;
    private static PacmanGame pacmanGame;
    private static GameManager gameManager;
    private static LevelGenerator levelGenerator;

    public PacmanGame(int nbLevel, float timeMultiplicator, String title){
        PacmanGame.nbLevel = nbLevel;
        currentLevel = 5;
        GameLoop.setTimeAnimation(timeMultiplicator);
        GameLoop.setTitle(title);
    }

    public static void createGame(int nbLevel, float timeMultiplicator, String title){
        pacmanGame = new PacmanGame(nbLevel, timeMultiplicator, title);
    }

    public static PacmanGame getGame(){
        return pacmanGame;
    }

    public void nextLevel(){
        currentLevel = (currentLevel % nbLevel) + 1;
        launch(1f);
    }

    public void stopGame(){
        // TODO : To change (link to the main menu)
        GameLoop.stopGame();
    }

    public void resetGame(){
        for(Entity e : levelGenerator.getInitPositionEntities().keySet()){
            resetEntity(e);
        }
    }

    public void resetEntity(Entity entity){
        Position actualPosition = levelGenerator.getMap().getPositionEntity(entity);
        Position initPosition = levelGenerator.getInitPositionEntities().get(entity);
        gameManager.getMap().swap((int)actualPosition.getX(), (int)actualPosition.getY(), (int)initPosition.getX(), (int)initPosition.getY(), entity);
        double new_x = initPosition.getX()*levelGenerator.getMap().getDimCellWdt();
        double new_y = initPosition.getY()*levelGenerator.getMap().getDimCellHgt();
        entity.setPosition(new Position(new_x, new_y));
    }

    public void launch(float timeMultiplicator){
        KeyboardController keyboard1 = (KeyboardController) levelGenerator.getPacman().getControllerComponent();
        KeyboardController keyboard2 = (KeyboardController) levelGenerator.getGhost().getControllerComponent();
        GeneralKeyboardController keyboardController = new GeneralKeyboardController(new ArrayList<>(Arrays.asList(keyboard1, keyboard2)));

        final long startNanoTime = System.nanoTime();

        AnimationTimer animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double time = (currentNanoTime - startNanoTime) * 10e-10 * timeMultiplicator;
                Timer.getInstance().setTime(time);
                try {
                    gameManager.update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        GameLoop.stopGame();
        SceneManager.getInstance().getStage().getScene().setOnKeyPressed(keyboardController.getEventHandler());
        GameLoop.setAnimationTimer(animationTimer);
        GameLoop.startAnimationTiemer();

        //GameLoop.setGameManager(gameManager);
        //GameLoop.startGame();
    }

    public LevelGenerator getLevelGenerator() {
        return levelGenerator;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void createMap(int lvl) {
        //levelGenerator = new LevelGenerator(512,512,"/Level/level" + lvl + ".txt");
        //gameManager = new GameManager(levelGenerator.getMap());
    }

    public static void main(String[] args) {
        PacmanGame.createGame(3, 1f, "Pacman");
        //PacmanGame.getGame().launch(1f);
    }
}
