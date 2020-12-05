package gameplay.ai;

import moteur.physics.Position;
import moteur.ai.AI;
import moteur.ai.BasicPathFinder;
import moteur.core_kernel.Entity;
import gameplay.physics.Displacement;

import java.util.List;

/**
 * Classe définissant l'ia de plus court chemin
 */
public class ShortestPathAI implements AI {
    private BasicPathFinder pathFinder;
    private Entity origin;
    private Entity target;
    private Displacement lastDisplacement;

    public ShortestPathAI(){
        lastDisplacement = Displacement.NOTHING;
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

        Position position_origin = pathFinder.getMap().getPositionEntity(origin);
        if(! canChangeDirection()){
            return;
        }

        List<Position> listPositions = pathFinder.pathFinding(origin, target);
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
}
