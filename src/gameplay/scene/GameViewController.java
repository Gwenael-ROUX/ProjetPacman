package gameplay.scene;

import gameplay.EntityType;
import gameplay.LevelGenerator;
import gameplay.events.EventWinAllLevel;
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
    private int nbLevel;

    private static Score score = new Score();;
    private static int sessionBestScore;

    LabelUI scoreUI = new LabelUI("Score: " + GameModel.getInstance().getPacmanModel().getScore(),350,-10);
    LabelUI vieUI = new LabelUI("vie Restante: " +  GameModel.getInstance().getPacmanModel().getPV(),50,-10);
    LabelUI bestScore ;



    public GameViewController(int nbLevel, int currentlevel, boolean twoPlayer) {
        this.nbLevel = nbLevel;
        this.twoPlayer = twoPlayer;
        this.endlevel = false;
        this.currentLvl = currentlevel;
        levelGenerator = new LevelGenerator(512,512,"/Level/level" + currentlevel + ".txt", twoPlayer);
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
            ++currentLvl;
            if (currentLvl > nbLevel) {
                displayMainTitle("Niveaux termines");
                EventManager.getEventManager().addEvent(new EventWinAllLevel(null, this, 20));
            } else {
                displayMainTitle("Changement de niveau");
                EventManager.getEventManager().addEvent(new EventChangeLevel(null, this, 20));
            }
        }
    }

    /**
     * Permet de savoir s'il reste des gommes dans le niveau
     * @return vrai s'il en reste et faux sinon
     */
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

    /**
     * Initialise la partie UI du score et de la vie
     */
    public void initUI(){
        scoreUI.setColor(Color.WHITE);
        scoreUI.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),25);

        vieUI.setColor(Color.RED);
        vieUI.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),25);

        bestScore.setColor(Color.WHITE);
        bestScore.changeFont(getClass().getResourceAsStream("/Font/CurlzMT.ttf"),22);

        gameView.addToScene(scoreUI.getLabel());
        gameView.addToScene(vieUI.getLabel());
        gameView.addToScene(bestScore.getLabel());
    }

    /**
     * Met à jour l'affichage du score et de la vie
     */
    public void updateUI(){
        scoreUI.update("Score: " + GameModel.getInstance().getPacmanModel().getScore());
        vieUI.update("Vie: " + GameModel.getInstance().getPacmanModel().getPV());
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

    /**
     * Affiche au milieu de la scene un texte
     * @param name contenu du texte
     */
    public void displayMainTitle(String name) {
        LabelUI labelChangeLvl = new LabelUI(name, gameView.getHeightScene() * 0.25, gameView.getHeightScene() * 0.5);
        labelChangeLvl.changeFont(getClass().getResourceAsStream("/Font/ARCADE_N.TTF"),20);
        labelChangeLvl.setColor(Color.YELLOW);
        gameView.addToScene(labelChangeLvl.getLabel());
    }

    /**
     *
     */
    public void getBackMenuWin() {
        GameModel.getInstance().resetPacMan();
        SceneManager.getInstance().setSceneView(new MenuController());
        SoundManager.getInstance().stopAllSound();
        GameLoop.setGameManager(null);
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
        bestScore =new LabelUI("bestScore: " + score.getScorefile(),180,470);
        if (sessionBestScore > Integer.parseInt(score.getScorefile())){
            bestScore.update("bestScore: " + sessionBestScore);
        }
    }
}
