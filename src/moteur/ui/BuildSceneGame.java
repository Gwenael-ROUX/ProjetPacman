package moteur.ui;

import javafx.scene.image.ImageView;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Map;

import java.util.List;

public class BuildSceneGame {
    private SceneGame sceneGame;

    public BuildSceneGame() {
        this.sceneGame = new SceneGame();
    }

    public void build(Map map) {
        for (List<Entity>[] ent : map.getMatrix()) {
            for (List<Entity> le : ent) {
                for(Entity e : le){
                    if (e != null){
                        sceneGame.getChildren().add(e.getGraphicsComponent().getCurrentImage());
                    }
                }
            }
        }
    }

    public SceneGame getSceneGame() {
        return sceneGame;
    }

    public void update() {
        for (int i = 0; i < sceneGame.getChildren().size(); i++) {
            if (((ImageView) sceneGame.getChildren().get(i)).getImage() == null)
                sceneGame.getChildren().remove(sceneGame.getChildren().get(i));
        }
    }
}
