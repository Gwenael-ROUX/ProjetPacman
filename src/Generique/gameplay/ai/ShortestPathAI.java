package Generique.gameplay.ai;

import Generique.Moteur.physics.Position;
import Generique.Moteur.ai.AI;
import Generique.Moteur.ai.BasicPathFinder;
import Generique.Moteur.core_kernel.Entity;
import Generique.gameplay.physics.Displacement;

import java.util.List;

public class ShortestPathAI implements AI {
    private BasicPathFinder pathFinder;
    private Entity origin;
    private Entity target;
    private Displacement lastDisplacement;

    public ShortestPathAI(){
        //this.pathFinder = pathFinder;
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
    public double move(){
        if(pathFinder == null) return 0.0;

        List<Position> listPositions = pathFinder.pathFinding(origin, target);
        Position position_origin = pathFinder.getMap().getPositionEntity(origin);

        if(listPositions.size() == 0)
            return lastDisplacement.orientation;

        Position nextPosition = (listPositions.size() == 1) ?
                listPositions.get(listPositions.size()-1) : listPositions.get(listPositions.size()-2);
        Displacement result;
        if(nextPosition.getX() != position_origin.getX()){
            if(nextPosition.getX() > position_origin.getX())
                result = Displacement.RIGHT;
            else
                result = Displacement.LEFT;
        } else {
            if(nextPosition.getY() > position_origin.getY())
                result = Displacement.DOWN;
            else
                result = Displacement.UP;
        }

        lastDisplacement = result;
        return result.orientation;
    }
}
