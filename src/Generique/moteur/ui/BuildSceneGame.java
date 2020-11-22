package Generique.moteur.ui;

import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.Map;
import Generique.moteur.graphique.GraphicsComponent;
import javafx.scene.canvas.GraphicsContext;

public class BuildSceneGame {
    private Map currentMap;
    private SceneGame sceneGame;

    public BuildSceneGame(Map map) {
        this.currentMap = map;
        this.sceneGame = new SceneGame();
        build();
    }

    public void build() {
        for (Entity[] ent : currentMap.getMatrix()) {
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

    public void update(Map map) {
        for (int i = 0; i < currentMap.getMatrix().length; ++i) {
            for (int j = 0; j < currentMap.getMatrix().length; ++j) {
                Entity e = currentMap.getEntity(i,j);
                if (e != null){
                    GraphicsContext gc = sceneGame.getGc();
                    GraphicsComponent graphicsComponent = e.getGraphicsComponent();
                    gc.clearRect(e.getPosition().getX(), e.getPosition().getY(), graphicsComponent.getWidth(), graphicsComponent.getHeight());
                    Entity newEntity = map.getEntity(i,j);
                    gc.drawImage(newEntity.getGraphicsComponent().getCurrentImage() ,newEntity.getPosition().getX(), newEntity.getPosition().getY(), newEntity.getGraphicsComponent().getWidth(), newEntity.getGraphicsComponent().getHeight());
                }
            }
        }
        this.currentMap = map;
    }
}
