package moteur.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Map;
import moteur.core_kernel.Timer;

import java.util.List;

public class SceneGame extends Pane {
    public SceneGame() {
        setStyle("-fx-background-color: #000000;");
    }

    public void initLevel(Map map) {
        for (List<Entity>[] ent : map.getMatrix()) {
            for (List<Entity> le : ent) {
                for(Entity e : le){
                    if (e != null){
                        getChildren().add(e.getGraphicsComponent().getCurrentImage());
                    }
                }
            }
        }
    }

    public void update() {
        for (int i = 0; i < getChildren().size(); i++) {
            if (((ImageView) getChildren().get(i)).getImage() == null)
                getChildren().remove(getChildren().get(i));
        }
    }
}
