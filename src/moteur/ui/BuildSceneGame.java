package moteur.ui;

import javafx.scene.image.ImageView;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BuildSceneGame {
    private GameView sceneGame;
    private Map map;
    public BuildSceneGame(Map map) {
        this.map = map;
        this.sceneGame = new GameView();
    }

    public void build() {
        Comparator<Entity> comparator = Comparator.comparingInt(o -> o.getGraphicsComponent().getLayer());
        sceneGame.setPrefWidth(map.getWidth() * map.getDimCellWdt());
        sceneGame.setPrefHeight(map.getHeight() * map.getDimCellHgt());
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

    public GameView getSceneGame() {
        return sceneGame;
    }

    public void update() {
        for (int i = 0; i < sceneGame.getChildren().size(); i++) {
            if (((ImageView) sceneGame.getChildren().get(i)).getImage() == null)
                sceneGame.getChildren().remove(sceneGame.getChildren().get(i));
            else{
            }
        }
    }
}
