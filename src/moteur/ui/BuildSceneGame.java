package moteur.ui;

import javafx.scene.image.ImageView;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Map;
import moteur.graphique.GraphicsComponent;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class BuildSceneGame {
    private Map currentMap;
    private SceneGame sceneGame;

    public BuildSceneGame(Map map) {
        this.currentMap = map;
        this.sceneGame = new SceneGame();
        build();
    }

    public void build() {
        for (List<Entity>[] ent : currentMap.getMatrix()) {
            for (List<Entity> le : ent) {
                for(Entity e : le){
                    if (e != null){
                        //GraphicsContext gc = sceneGame.getGc();
                        GraphicsComponent graphicsComponent = e.getGraphicsComponent();
                        ImageView imageView = graphicsComponent.getCurrentImage();
                        imageView.setX(e.getPosition().getX());
                        imageView.setY(e.getPosition().getY());
                        imageView.setFitHeight(graphicsComponent.getHeight());
                        imageView.setFitWidth(graphicsComponent.getWidth());
                        sceneGame.getChildren().add(imageView);
                        //gc.drawImage(graphicsComponent.getCurrentImage(), e.getPosition().getX(), e.getPosition().getY(), graphicsComponent.getWidth(), graphicsComponent.getHeight());
                        //System.out.println(e.getName() + " : " + e.getPosition().getX() + "   " + e.getPosition().getY());
                    }
                }
            }
        }
    }

    public SceneGame getSceneGame() {
        return sceneGame;
    }

    public void update(Map map) {
        GraphicsContext gc = sceneGame.getGc();
        sceneGame.getChildren().removeAll(sceneGame.getChildren());
        
        for (int i = 0; i < currentMap.getMatrix().length; ++i) {
            for (int j = 0; j < currentMap.getMatrix()[i].length; ++j) {
                for(Entity e : map.getMatrix()[i][j]){
                    if (e != null){
                        GraphicsComponent graphicsComponent = e.getGraphicsComponent();
                        ImageView imageView = graphicsComponent.getCurrentImage();
                        imageView.setX(e.getPosition().getX());
                        imageView.setY(e.getPosition().getY());
                        imageView.setFitHeight(graphicsComponent.getHeight());
                        imageView.setFitWidth(graphicsComponent.getWidth());
                        sceneGame.getChildren().add(imageView);
                        //gc.drawImage(e.getGraphicsComponent().getCurrentImage(), e.getPosition().getX(), e.getPosition().getY(), e.getGraphicsComponent().getWidth(), e.getGraphicsComponent().getHeight());
                    }
                }
            }
        }

        this.currentMap = map;
    }
}
