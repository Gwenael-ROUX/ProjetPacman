package gameplay;

import moteur.core_kernel.GameLoop;
import gameplay.scene.MenuController;

/**
 * Classe permettant de lancer le jeu pacnoel
 */
public class PacnoelLaunch {

    public static void main(String[] args) {
        GameLoop.setTitle("PacNoel");
        MenuController menuController = new MenuController();
        GameLoop.setSceneController(menuController);
        GameLoop.setPathIcon("/Image/pacnoel/right.png");
        GameLoop.startGame();
    }
}
