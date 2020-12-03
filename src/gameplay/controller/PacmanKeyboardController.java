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
    private int last_pv;

    public PacmanKeyboardController(Map map, PacmanModel pacmanModel){
        this.map = map;
        this.pacmanModel = pacmanModel;
        last_pv = pacmanModel.getPV();
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
        if(!pacmanModel.isDead()){
            updateMove(entity);
            updateGraphics(entity);

            entity.setOrientation(move.orientation);
        }
    }

    private void updateGraphics(Entity entity){
        if(move != Displacement.NOTHING)
            if (pacmanModel.isNoel())
                entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(move.orientation.toString()+EntityType.TREE.name);
            else
                entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(move.orientation.toString());
    }

    private void updateMove(Entity entity){
        if(last_pv != pacmanModel.getPV()){
            move = Displacement.NOTHING;
            nextMove = Displacement.NOTHING;
            //entity.getGraphicsComponent().getAnimationManager().setCurrentAnimation(move.orientation.toString());

            last_pv = pacmanModel.getPV();
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

                /*for(Entity e : entities)
                    System.out.println(e.getName());
                showMap(map);
                System.out.println(move);
                System.out.println(nextMove);
                System.out.println("===================");*/
            }
        }
    }

    private boolean canCross(List<Entity> entities){
        for(Entity entity : entities){
            if(EntityType.WALL.name.equals(entity.getName()))
                return false;
        }
        return true;
    }

    private boolean isPacman(List<Entity> entities){
        for(Entity entity : entities){
            if(EntityType.PACMAN.name.equals(entity.getName()))
                return true;
        }
        return false;
    }

    private void showMap(Map map){
        for(int y = 0; y < map.getHeight(); y++){
            for(int x = 0; x < map.getWidth(); x++){
                if(isPacman(map.getEntity(x, y)))
                    System.out.print(" X ");
                else if(canCross(map.getEntity(x, y)))
                    System.out.print("   ");
                else
                    System.out.print(" O ");
            }
            System.out.println();
        }
    }
}

