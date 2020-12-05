package moteur.ui;

import gameplay.EntityType;
import gameplay.LevelGenerator;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import moteur.controller.GeneralKeyboardController;
import moteur.controller.KeyboardController;
import moteur.core_kernel.*;
import moteur.core_kernel.Map;
import moteur.physics.Position;

import java.util.*;

public class GameViewController implements SceneController{
    private GameManager gameManager;
    private LevelGenerator levelGenerator;
    private GameView gameView;
    private int currentLvl;

    public GameViewController(int level) {
        levelGenerator = new LevelGenerator(512,512,"/Level/level" + level + ".txt");
        currentLvl = level;
        gameManager = new GameManager(levelGenerator.getMap());
        gameView = new GameView();
        GameLoop.setGameManager(gameManager);
    }

    public void resetGame(){
        for(Entity e : levelGenerator.getInitPositionEntities().keySet()){
            resetEntity(e);
        }
        gameManager.breakCurrentUpdate();
    }

    public void resetEntity(Entity entity) {
        Position actualPosition = levelGenerator.getMap().getPositionEntity(entity);
        Position initPosition = levelGenerator.getInitPositionEntities().get(entity);
        gameManager.getMap().swap((int)actualPosition.getX(), (int)actualPosition.getY(), (int)initPosition.getX(), (int)initPosition.getY(), entity);
        double new_x = initPosition.getX()*levelGenerator.getMap().getDimCellWdt();
        double new_y = initPosition.getY()*levelGenerator.getMap().getDimCellHgt();
        entity.setPosition(new Position(new_x, new_y));
    }

    @Override
    public void init() {
        Comparator<Entity> comparator = Comparator.comparingInt(o -> o.getGraphicsComponent().getLayer());
        KeyboardController keyboard1 = (KeyboardController) levelGenerator.getPacman().getControllerComponent();
        KeyboardController keyboard2 = (KeyboardController) levelGenerator.getGhost().getControllerComponent();
        GeneralKeyboardController keyboardController = new GeneralKeyboardController(new ArrayList<>(Arrays.asList(keyboard1, keyboard2)));
        SceneManager.getInstance().getStage().getScene().setOnKeyPressed(keyboardController.getEventHandler());
        Map map = levelGenerator.getMap();
        gameView.setOnKeyPressed(keyboardController.getEventHandler());
        gameView.setPrefWidth(map.getWidth() * map.getDimCellWdt());
        gameView.setPrefHeight(map.getHeight() * map.getDimCellHgt());

        SceneManager.getInstance().getStage().getScene().setOnKeyPressed(keyboardController.getEventHandler());

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

        if (!isGumsExist()) {
            gameView.getChildren().clear();
            gameManager.breakCurrentUpdate();
            ++currentLvl;
            levelGenerator = new LevelGenerator(512,512,"/Level/level" + currentLvl + ".txt");
            gameManager.setMap(levelGenerator.getMap());
            init();
        }
    }

    private boolean isGumsExist() {
        for (List<Entity>[] ent : gameManager.getMap().getMatrix()) {
            for (List<Entity> le : ent) {
                for(Entity e : le){
                    if (e != null){
                        if (e.getName().equals(EntityType.GOMME.name))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Parent getView() {
        return gameView;
    }
}
