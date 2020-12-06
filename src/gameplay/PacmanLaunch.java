package gameplay;

import moteur.core_kernel.GameLoop;
import gameplay.scene.MenuController;


public class PacmanLaunch {

    public static void main(String[] args) {
        GameLoop.setTitle("PacNoël");
        MenuController menuController = new MenuController();
        GameLoop.setSceneController(menuController);
        GameLoop.startGame();
    }
}
