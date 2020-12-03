package moteur.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Map;
import moteur.core_kernel.Timer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameView extends SceneView {

    @Override
    public void init(Map map, SceneManager sceneManager) {
        setStyle("-fx-background-color: #000000;");

        Comparator<Entity> comparator = Comparator.comparingInt(o -> o.getGraphicsComponent().getLayer());
        setPrefWidth(map.getWidth() * map.getDimCellWdt());
        setPrefHeight(map.getHeight() * map.getDimCellHgt());
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
            getChildren().add(e.getGraphicsComponent().getCurrentImage());
        }
    }

    @Override
    public void update(Map map) {
        for (int i = 0; i < getChildren().size(); i++) {
            if (((ImageView) getChildren().get(i)).getImage() == null)
                getChildren().remove(getChildren().get(i));
            else{
            }
        }
    }
}
