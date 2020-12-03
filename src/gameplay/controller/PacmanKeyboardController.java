package gameplay.controller;

import gameplay.EntityType;
import gameplay.model.PacmanModel;
import moteur.controller.KeyboardController;
import moteur.core_kernel.Entity;
import gameplay.physics.Displacement;
import moteur.core_kernel.Map;
import moteur.physics.Position;

import java.util.ArrayList;
import java.util.List;

public class PacmanKeyboardController extends KeyboardController {
    private Displacement nextMove;
    private Displacement move;
    private PacmanModel pacmanModel;
    private Map map;

    public PacmanKeyboardController(Map map, PacmanModel pacmanModel){
        this.map = map;
        this.pacmanModel = pacmanModel;
        nextMove = Displacement.NOTHING;
        move = Displacement.NOTHING;
        createHandler();
    }

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

    @Override
    public void update(Entity entity){
        if(nextMove != Displacement.NOTHING){
            Position position = map.getPositionEntity(entity);
            int x = (int)position.getX(), y = (int)position.getY();
            List<Entity> entities = new ArrayList<>();
            //System.out.println(entity.getPosition().getX()%map.getDimCellWdt());
            //System.out.println(entity.getPosition().getY()%map.getDimCellHgt());

            if(entity.getPosition().getX()%map.getDimCellWdt() == 0 && entity.getPosition().getY()%map.getDimCellHgt() == 0){
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

                if(nextMove != Displacement.NOTHING && canCross(entities)) {
                    move = nextMove;
                    nextMove = Displacement.NOTHING;
                }
            }
        }

        if(move != Displacement.NOTHING)
            if (pacmanModel.isNoel())
                entity.getGraphicsComponent().getAnimation().setCurrentAnimation(move.orientation.toString()+EntityType.TREE.name);
            else
                entity.getGraphicsComponent().getAnimation().setCurrentAnimation(move.orientation.toString());
        entity.setOrientation(move.orientation);
    }

    private boolean canCross(List<Entity> entities){
        for(Entity entity : entities){
            if(entity.getName().equals(EntityType.WALL.name))
                return false;
        }
        return true;
    }
}

