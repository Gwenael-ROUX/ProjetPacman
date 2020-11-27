package moteur.ui;

import javafx.collections.transformation.SortedList;
import javafx.scene.image.ImageView;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BuildSceneGame {
    private SceneGame sceneGame;

    public BuildSceneGame() {
        this.sceneGame = new SceneGame();
    }

    public void build(Map map) {
        Comparator<Entity> comparator = Comparator.comparingInt(o -> o.getGraphicsComponent().getLayer());

        ArrayList<Entity> sortedList = new ArrayList<>();

        for (List<Entity>[] ent : map.getMatrix()) {
            for (List<Entity> le : ent) {
                for(Entity e : le){
                    if (e != null){
                        sortedList.add(e);
                    }
                }
            }
        }

        sortedList.sort(comparator);
        for (Entity e: sortedList){
            sceneGame.getChildren().add(e.getGraphicsComponent().getCurrentImage());
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
