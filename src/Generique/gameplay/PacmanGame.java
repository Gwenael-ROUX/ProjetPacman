package Generique.gameplay;

import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.GameLoop;
import Generique.moteur.core_kernel.GameManager;
import Generique.moteur.ui.BuildSceneGame;
import Generique.moteur.ui.SceneManager;
import Generique.moteur.ui.SceneManager2;

public class PacmanGame {
    public static void main(String[] args) {
        LevelGenerator levelGenerator = new LevelGenerator(512,512,"Level/level1");
        GameManager gameManager = new GameManager(levelGenerator.getMap());
        GameLoop.launch();
    }
    public void init() {
        LevelGenerator levelGenerator = new LevelGenerator(512,512,"Level/level1");
        GameManager gameManager = new GameManager(levelGenerator.getMap());
    }
}
