package gameplay;

import javafx.animation.AnimationTimer;
import moteur.controller.GeneralKeyboardController;
import moteur.controller.KeyboardController;
import moteur.core_kernel.GameLoop;
import moteur.core_kernel.GameManager;
import moteur.core_kernel.Timer;
import moteur.ui.GameViewController;
import moteur.ui.MenuController;

import java.util.ArrayList;
import java.util.Arrays;

public class PacmanLaunch {
    private static PacmanLaunch pacmanLaunch;

    public PacmanLaunch(float timeMultiplicator, String title) {
        GameLoop.setTimeAnimation(timeMultiplicator);
        GameLoop.setTitle(title);
    }

    public void launch() {
        GameLoop.setTitle("Pacman");
        MenuController menuController = new MenuController();
        GameLoop.setSceneController(menuController);
        GameLoop.startGame();
    }

    public static void createGame(float timeMultiplicator, String title){
        pacmanLaunch = new PacmanLaunch(timeMultiplicator, title);
    }

    public static PacmanLaunch getGame(){
        return pacmanLaunch;
    }

    public static void main(String[] args) {
        PacmanLaunch.createGame(1f, "Pacman");
        PacmanLaunch.getGame().launch();
    }
}
