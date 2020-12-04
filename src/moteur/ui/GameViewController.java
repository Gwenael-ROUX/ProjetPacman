package moteur.ui;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameViewController implements SceneController{
    private GameView gameView;

    @Override
    public void init(Map map) {
        gameView = new GameView();
        Comparator<Entity> comparator = Comparator.comparingInt(o -> o.getGraphicsComponent().getLayer());
        gameView.setPrefWidth(map.getWidth() * map.getDimCellWdt());
        gameView.setPrefHeight(map.getHeight() * map.getDimCellHgt());
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
            gameView.getChildren().add(e.getGraphicsComponent().getCurrentImage());
        }
    }

    @Override
    public void update(Map map) {
        for (int i = 0; i < gameView.getChildren().size(); i++) {
            if (((ImageView) gameView.getChildren().get(i)).getImage() == null)
                gameView.getChildren().remove(gameView.getChildren().get(i));
            else{
            }
        }
    }

    @Override
    public Parent getView() {
        return gameView;
    }
}
