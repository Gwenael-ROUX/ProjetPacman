package gameplay.controller;

import gameplay.EntityType;
import gameplay.model.GameModel;
import gameplay.model.PacmanModel;
import moteur.controller.KeyboardController;
import moteur.core_kernel.Entity;
import gameplay.physics.Displacement;
import moteur.core_kernel.Map;
import moteur.physics.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe definissant les inputs pour le deuxième joueur
 */
public class PacmanKeyboardController extends KeyboardController {
    private Displacement nextMove;
    private Displacement move;
    private Map map;
    private int last_pv;

    public PacmanKeyboardController(Map map){
        this.map = map;
        last_pv = GameModel.getInstance().getPacmanModel().getPV();
        nextMove = Displacement.NOTHING;
        move = Displacement.NOTHING;
        createHandler();
    }

    /**
     * fonction recuperant l'input du joueurs pour définir le prochain déplacement
     */
    private void createHandler(){
        createHandler(KeyEvent -> {
            switch (KeyEvent) {
                case Z :
                    nextMove = Displacement.UP;
                    break;
                case Q :
                    nextMove = Displacement.LEFT;
                    break;
                case S :
                    nextMove = Displacement.DOWN;
                    break;
                case D :
                    nextMove = Displacement.RIGHT;
                    break;
                default:
                    //nextMove = Displacement.NOTHING;
                    break;
            }
        });
    }

    /**
     * Fonction mettant à jour l'orientation
     * @param entity
     */
    @Override
    public void update(Entity entity){
        if(!GameModel.getInstance().getPacmanModel().isDead()){
            updateMove(entity);
            updateGraphics(entity);

            entity.setOrientation(move.orientation);
        }
    }

    /**
     * Fonction choisissant la bonne animation
     * @param entity
     */
    private void updateGraphics(Entity entity){
        if(move != Displacement.NOTHING)
            if (GameModel.getInstance().getPacmanModel().isNoel())
                entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(move.orientation.toString()+EntityType.TREE.name);
            else
                entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(move.orientation.toString());
    }

    /**
     * Fonction verifiant si la direction entré est valable pour le déplacement de l'entity
     * @param entity
     */
    private void updateMove(Entity entity){
        if(last_pv != GameModel.getInstance().getPacmanModel().getPV() || GameModel.getInstance().getPacmanModel().isDead()){
            move = Displacement.NOTHING;
            nextMove = Displacement.NOTHING;
            //entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(move.orientation.toString());

            last_pv = GameModel.getInstance().getPacmanModel().getPV();
        }

        if(nextMove != Displacement.NOTHING){
            Position position = map.getPositionEntity(entity);
            int x = (int)position.getX(), y = (int)position.getY();
            List<Entity> entities = new ArrayList<>();

            if((entity.getOrientation() == null)
                    || ((entity.getPosition().getX()%map.getDimCellWdt() <= entity.getPhysicsComponent().getSpeed()))
                    && (entity.getPosition().getY()%map.getDimCellHgt() <= entity.getPhysicsComponent().getSpeed())) {
                switch (nextMove) {
                    case UP :
                        entities = map.getEntity(x, y-1);
                        break;
                    case DOWN :
                        entities = map.getEntity(x, y+1);
                        break;
                    case RIGHT :
                        entities = map.getEntity(x+1, y);
                        break;
                    case LEFT :
                        entities = map.getEntity(x-1, y);
                        break;
                }

                if(canCross(entities)) {
                    double new_x = entity.getPosition().getX() - (entity.getPosition().getX()%map.getDimCellWdt());
                    double new_y = entity.getPosition().getY() - (entity.getPosition().getY()%map.getDimCellHgt());
                    Position new_position = new Position(new_x, new_y);
                    entity.setPosition(new_position);
                    entity.getPhysicsComponent().getCollider().update(new_position);
                    move = nextMove;
                    nextMove = Displacement.NOTHING;
                }
            }
        }
    }

    /**
     * fonction permettant de savoir si dans la liste d'entity passé en paramètre
     * toutes les entity sont traversable
     * @param entities
     * @return
     */
    private boolean canCross(List<Entity> entities){
        for(Entity entity : entities){
            if(EntityType.WALL.name.equals(entity.getName()))
                return false;
        }
        return true;
    }
}

