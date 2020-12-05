package gameplay.scene;

import gameplay.EntityType;
import gameplay.LevelGenerator;
import gameplay.model.GameModel;
import gameplay.events.EventChangeLevel;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import moteur.controller.GeneralKeyboardController;
import moteur.controller.KeyboardController;
import moteur.core_kernel.*;
import moteur.core_kernel.Map;
import moteur.sound.SoundManager;
import moteur.ui.LabelUI;
import moteur.ui.SceneController;
import moteur.ui.SceneManager;
import moteur.ui.ViewFX;

import java.util.*;

public class GameViewController implements SceneController {
    private GameManager gameManager;
    private LevelGenerator levelGenerator;
    private ViewFX gameView;
    private int currentLvl;
    private boolean twoPlayer;
    private boolean endlevel;

    public GameViewController(int level, boolean twoPlayer) {
        this.twoPlayer = twoPlayer;
        this.endlevel = false;
        levelGenerator = new LevelGenerator(512,512,"/Level/level" + level + ".txt", twoPlayer);
        currentLvl = level;
        gameManager = new GameManager(levelGenerator.getMap());
        GameModel.getInstance().setLevelGenerator(levelGenerator);
        gameView = new GameView();
        GameLoop.setGameManager(gameManager);
    }

    @Override
    public void init() {
        Comparator<Entity> comparator = Comparator.comparingInt(o -> o.getGraphicsComponent().getLayer());
        KeyboardController keyboard1 = (KeyboardController) levelGenerator.getPacman().getControllerComponent();
        GeneralKeyboardController keyboardController;
        if (twoPlayer) {
            KeyboardController keyboard2 = (KeyboardController) levelGenerator.getGhost().getControllerComponent();
            keyboardController = new GeneralKeyboardController(new ArrayList<>(Arrays.asList(keyboard1, keyboard2)));
        } else
            keyboardController = new GeneralKeyboardController(new ArrayList<>(Arrays.asList(keyboard1)));
        SceneManager.getInstance().getStage().getScene().setOnKeyPressed(keyboardController.getEventHandler());
        Map map = levelGenerator.getMap();
        gameView.setKeyPressed(keyboardController.getEventHandler());
        gameView.setWidthScene(map.getWidth() * map.getDimCellWdt());
        gameView.setHeightScene(map.getHeight() * map.getDimCellHgt());

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
            gameView.addToScene(e.getGraphicsComponent().getCurrentImage());
        }
    }

    @Override
    public void update(Map map) {
        for (int i = 0; i < gameView.getChildren().size(); i++) {
            if (gameView.getChildren().get(i) instanceof ImageView && ((ImageView) gameView.getChildren().get(i)).getImage() == null)
                gameView.getChildren().remove(gameView.getChildren().get(i));
        }

        if (!isGumsExist() && !endlevel) {
            endlevel = true;
            SoundManager.getInstance().addSound("pacman_beginning.wav", "intro", false, 0.2f, 0L);
            LabelUI labelChangeLvl = new LabelUI("Changement de niveau", gameView.getHeightScene() * 0.1, gameView.getHeightScene() * 0.5);
            labelChangeLvl.changeFont(getClass().getResourceAsStream("/Font/ARCADE_N.TTF"),20);
            labelChangeLvl.setColor(Color.YELLOW);
            gameView.addToScene(labelChangeLvl.getLabel());
            ++currentLvl;
            EventManager.getEventManager().addEvent(new EventChangeLevel(null, this, 20));
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

    public GameManager getGameManager() {
        return gameManager;
    }

    public ViewFX getGameView() {
        return gameView;
    }

    public void setNewLevel() {
        this.levelGenerator = new LevelGenerator(512,512,"/Level/level" + currentLvl + ".txt",twoPlayer);
        GameModel.getInstance().setLevelGenerator(levelGenerator);
    }

    public LevelGenerator getLevelGenerator() {
        return levelGenerator;
    }

    public void setEndlevel(boolean endlevel) {
        this.endlevel = endlevel;
    }
}
