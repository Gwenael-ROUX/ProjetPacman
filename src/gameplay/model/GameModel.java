package gameplay.model;

import gameplay.LevelGenerator;
import gameplay.physics.Displacement;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Map;
import moteur.physics.Position;

/**
 * Classe stockant les informations des stats du jeu
 */
public class GameModel {
    private static GameModel gameModel;
    private LevelGenerator levelGenerator;
    private PacmanModel pacmanModel;

    private GameModel(LevelGenerator levelGenerator){
        gameModel = this;
        this.levelGenerator = levelGenerator;
        pacmanModel = new PacmanModel();
    }

    public static GameModel getInstance(){
        if(gameModel == null)
            gameModel = new GameModel(null);
        return gameModel;
    }

    /**
     * pour reset le jeu, reset des positions de chaque entity
     */
    public void resetGame(){
        for(Entity e : levelGenerator.getInitPositionEntities().keySet()){
            resetEntity(e);
            if(e.getName().equals("pacman"))
                e.setOrientation(Displacement.NOTHING.orientation);
        }
    }

    /**
     * re-placement de la position initiale dans la scene d'une entity
     * @param entity
     */
    public void resetEntity(Entity entity) {
        Position actualPosition = levelGenerator.getMap().getPositionEntity(entity);
        Position initPosition = levelGenerator.getInitPositionEntities().get(entity);
        levelGenerator.getMap().swap((int)actualPosition.getX(), (int)actualPosition.getY(), (int)initPosition.getX(), (int)initPosition.getY(), entity);
        double new_x = initPosition.getX()*levelGenerator.getMap().getDimCellWdt();
        double new_y = initPosition.getY()*levelGenerator.getMap().getDimCellHgt();
        entity.setPosition(new Position(new_x, new_y));
        entity.getPhysicsComponent().update(entity);
    }

    public void resetPacMan(){
        pacmanModel = new PacmanModel();
    }

    public PacmanModel getPacmanModel() {
        return pacmanModel;
    }

    public Map getMap(){
        if(levelGenerator != null)
            return levelGenerator.getMap();
        return null;
    }

    public void setLevelGenerator(LevelGenerator levelGenerator){
        this.levelGenerator = levelGenerator;
        pacmanModel.setNoel(false);
    }


}
