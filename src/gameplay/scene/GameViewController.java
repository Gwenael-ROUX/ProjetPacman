package gameplay.scene;

import gameplay.EntityType;
import gameplay.LevelGenerator;
import gameplay.model.GameModel;
import gameplay.Score;
import gameplay.events.EventChangeLevel;
import moteur.controller.GeneralKeyboardController;
import moteur.controller.KeyboardController;
import moteur.core_kernel.*;
import moteur.core_kernel.Map;
import moteur.sound.SoundManager;
import moteur.ui.*;
import java.util.*;
import java.util.List;

public class GameViewController implements SceneController {
    private GameManager gameManager;
    private LevelGenerator levelGenerator;
    private ViewFX gameView;
    private int currentLvl;
    private boolean twoPlayer;
    private boolean endlevel;

    private static Score score = new Score();;
    private static int sessionBestScore;

    LabelUI scoreUI = new LabelUI("Score: " + GameModel.getInstance().getPacmanModel().getScore(),350,-10);
    LabelUI vieUI = new LabelUI("vie Restante: " +  GameModel.getInstance().getPacmanModel().getPV(),50,-10);
    LabelUI bestScore ;



    public GameViewController(int level, boolean twoPlayer) {
        this.twoPlayer = twoPlayer;
        this.endlevel = false;
        levelGenerator = new LevelGenerator(512,512,"/Level/level" + level + ".txt", twoPlayer);
        currentLvl = level;
        gameManager = new GameManager(levelGenerator.getMap());
        GameModel.getInstance().setLevelGenerator(levelGenerator);
        gameView = new GameView();
        GameLoop.setGameManager(gameManager);
        setBestScore();
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
        initUI();
    }

    @Override
    public void update(Map map) {
        for (int i = 0; i < gameView.getChildren().size(); i++) {
            //if (gameView.getChildren().get(i) instanceof ImageView && ((ImageView) gameView.getChildren().get(i)).getImage() == null)
            if (gameView.getChildren().get(i) == null)
                gameView.getChildren().remove(gameView.getChildren().get(i));
        }
        updateUI();

        if (!isGumsExist() && !endlevel) {
            endlevel = true;
            SoundManager.getInstance().stopAllSound();
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

    public void initUI(){
        scoreUI.setColor(Color.WHITE);
        scoreUI.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),25);

        vieUI.setColor(Color.RED);
        vieUI.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),25);

        bestScore.setColor(Color.WHITE);
        bestScore.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),25);

        gameView.addToScene(scoreUI.getLabel());
        gameView.addToScene(vieUI.getLabel());
        gameView.addToScene(bestScore.getLabel());
    }
    public void updateUI(){
        scoreUI.update("Score: " + GameModel.getInstance().getPacmanModel().getScore());
        vieUI.update("Vie: " + GameModel.getInstance().getPacmanModel().getPV());
        //bestScore.update("bestScore: " + score.getScorefile());
    }

    @Override
    public ViewFX getView() {
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

    public static Score getScore() {
        return score;
    }

    public static void setSessionBestScore(int sessionBestScore) {
        GameViewController.sessionBestScore = sessionBestScore;
    }

    public void setBestScore(){
        bestScore =new LabelUI("bestScore: " + score.getScorefile(),200,465);
        if (sessionBestScore > Integer.parseInt(score.getScorefile())){
            bestScore.update("bestScore: " + sessionBestScore);
        }
    }
}
