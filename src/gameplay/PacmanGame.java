package gameplay;

import moteur.core_kernel.GameLoop;
import moteur.core_kernel.GameManager;

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
