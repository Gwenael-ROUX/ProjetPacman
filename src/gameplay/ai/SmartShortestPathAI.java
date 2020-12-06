package gameplay.ai;

import gameplay.model.GameModel;
import gameplay.physics.GhostPhysics;
import moteur.physics.BoxCollider;
import moteur.physics.PhysicsComponent;
import moteur.physics.Position;
import moteur.ai.AI;
import moteur.ai.BasicPathFinder;
import moteur.core_kernel.Entity;
import gameplay.physics.Displacement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe définissant l'ia de plus court chemin
 */
public class SmartShortestPathAI implements AI {
    private BasicPathFinder pathFinder;
    private Entity origin;
    private Entity anticip_target;
    private Entity target;
    private Displacement lastDisplacement;
    private int nbAnticipate;
    private List<Entity> lastEntities;

    public SmartShortestPathAI(){
        lastDisplacement = Displacement.NOTHING;
        nbAnticipate = 5;
    }

    public void setOrigin(Entity origin) {
        this.origin = origin;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public void setPathFinder(BasicPathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    /**
     * Fonction permettant de definir la prochaine position de l'entity passer en paramètre
     * @param entity
     */
    @Override
    public void update(Entity entity){

        if(pathFinder == null) return;
        if(! canChangeDirection()) return;

        Position position_origin = pathFinder.getMap().getPositionEntity(origin);
        List<Position> listPositions = getAnticipPosition();
        if(listPositions.size() == 0){
            entity.setOrientation(lastDisplacement.orientation);
            return;
        }

        Position nextPosition = (listPositions.size() == 1) ?
                listPositions.get(0) : listPositions.get(listPositions.size()-2);
        Displacement result;
        if(nextPosition.getX() != position_origin.getX()){
            if(nextPosition.getX() > position_origin.getX())
                result = Displacement.RIGHT;
            else if(nextPosition.getX() < position_origin.getX())
                result = Displacement.LEFT;
            else
                result = Displacement.NOTHING;
        } else {
            if(nextPosition.getY() > position_origin.getY())
                result = Displacement.DOWN;
            else if(nextPosition.getY() < position_origin.getY())
                result = Displacement.UP;
            else
                result = Displacement.NOTHING;
        }

        lastDisplacement = result;
        if(result != Displacement.NOTHING)
            entity.setOrientation(result.orientation);
    }

    /**
     * fonction permettant de detecter si le changement de direction est possible
     * @return
     */
    private boolean canChangeDirection(){
        return (origin.getPosition().getX()%pathFinder.getMap().getMap().getDimCellWdt() == 0)
            && (origin.getPosition().getY()%pathFinder.getMap().getMap().getDimCellHgt() == 0);
    }

    private List<Position> getAnticipPosition(){
        anticip_target = new Entity();
        anticip_target.setOrientation(target.getOrientation());
        anticip_target.setPosition(new Position(target.getPosition().getX(), target.getPosition().getY()));
        anticip_target.setControllerComponent(null);
        anticip_target.setGraphicsComponent(null);

        Position position1 = new Position(target.getPosition().getX(), target.getPosition().getY());
        Position position2 = new Position(target.getPosition().getX() + GameModel.getInstance().getMap().getDimCellWdt(), target.getPosition().getY() + GameModel.getInstance().getMap().getDimCellHgt());
        PhysicsComponent physicsComponent = new GhostPhysics(1, new BoxCollider(position1, position2));
        anticip_target.setPhysicsComponent(physicsComponent);
        for(int i = nbAnticipate; i > 0; i--)
            anticip_target.getPhysicsComponent().update(anticip_target);

        int x = (int) ((anticip_target.getPosition().getX() + GameModel.getInstance().getMap().getDimCellWdt()/2) / GameModel.getInstance().getMap().getDimCellWdt());
        int y = (int) ((anticip_target.getPosition().getY() + GameModel.getInstance().getMap().getDimCellHgt()/2) / GameModel.getInstance().getMap().getDimCellHgt());
        GameModel.getInstance().getMap().addEntity(x, y, anticip_target);
        List<Position> listPositions = pathFinder.pathFinding(origin, anticip_target);
        GameModel.getInstance().getMap().deleteEntity(new Position(x, y), anticip_target);

        return listPositions;
    }
}
