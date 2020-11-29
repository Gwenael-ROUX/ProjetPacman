package gameplay;

import moteur.controller.GeneralKeyboardController;
import moteur.controller.KeyboardController;
import moteur.core_kernel.GameLoop;
import moteur.core_kernel.GameManager;

import java.util.ArrayList;
import java.util.Arrays;

public class PacmanGame {

    private int nbLevel;
    private int currentLevel;
    private static PacmanGame pacmanGame;
    private static GameManager gameManager;

    private PacmanGame(int nbLevel, float timeMultiplicator, String title){
        this.nbLevel = nbLevel;
        currentLevel = 1;
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
        launch();
    }

    public void stopGame(){
        // TODO : To change (link to the main menu)
        GameLoop.stopGame();
    }

    public void launch(){
        LevelGenerator levelGenerator = new LevelGenerator(512,512,"/Level/level" + currentLevel + ".txt");
        gameManager = new GameManager(levelGenerator.getMap());

        KeyboardController keyboard1 = (KeyboardController) levelGenerator.getPacman().getControllerComponent();
        KeyboardController keyboard2 = (KeyboardController) levelGenerator.getGhost().getControllerComponent();
        GeneralKeyboardController keyboardController = new GeneralKeyboardController(new ArrayList<>(Arrays.asList(keyboard1, keyboard2)));

        GameLoop.setKeyboardController(keyboardController);
        GameLoop.setGameManager(gameManager);
        GameLoop.startGame();
    }

    public static void main(String[] args) {
        PacmanGame.createGame(3, 1f, "Pacman");
        PacmanGame.getGame().launch();
    }
}
