package Generique.moteur.ui;

import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.Map;
import Generique.moteur.graphique.GraphicsComponent;
import javafx.scene.canvas.GraphicsContext;

public class BuildSceneGame {
    private Map map;
    private SceneGame sceneGame;

    public BuildSceneGame(Map map) {
        this.map = map;
        this.sceneGame = new SceneGame();
        build();
    }

    public void build() {
        for (Entity[] ent : map.getMatrix()) {
            for (Entity e : ent) {
                if (e != null){
                    GraphicsContext gc = sceneGame.getGc();
                    GraphicsComponent graphicsComponent = e.getGraphicsComponent();
                    gc.drawImage(graphicsComponent.getCurrentImage() ,e.getPosition().getX(), e.getPosition().getY(), graphicsComponent.getWidth(), graphicsComponent.getHeight());
                }
            }
        }
    }

    public SceneGame getSceneGame() {
        return sceneGame;
    }

    public void moveImage() {

    }
}
