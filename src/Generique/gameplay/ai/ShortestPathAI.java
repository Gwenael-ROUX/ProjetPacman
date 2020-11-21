package Generique.gameplay.ai;

import Generique.moteur.physics.Position;
import Generique.moteur.ai.AI;
import Generique.moteur.ai.BasicPathFinder;
import Generique.moteur.core_kernel.Entity;
import Generique.gameplay.physics.Displacement;

import java.util.List;

public class ShortestPathAI implements AI {
    private BasicPathFinder pathFinder;
    private Entity origin;
    private Entity target;
    private Displacement lastDisplacement;

    public ShortestPathAI(){
        lastDisplacement = Displacement.NOTHING;
    }

    public ShortestPathAI(Entity origin, Entity target, BasicPathFinder pathFinder){
        this.origin = origin;
        this.target = target;
        this.pathFinder = pathFinder;
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

    @Override
    public void update(Entity entity){
        if(pathFinder == null) return;

        List<Position> listPositions = pathFinder.pathFinding(origin, target);
        Position position_origin = pathFinder.getMap().getPositionEntity(origin);

        if(listPositions.size() == 0){
            entity.setOrientation(lastDisplacement.orientation);
            return;
        }

        Position nextPosition = (listPositions.size() == 1) ?
                listPositions.get(listPositions.size()-1) : listPositions.get(listPositions.size()-2);
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
}
