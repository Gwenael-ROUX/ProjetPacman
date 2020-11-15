package moteurs.controllers.ai;
import gameplay.EntityCharacter;
import moteurs.Entity;
import moteurs.Position;
import moteurs.physics.Displacement;
import java.util.ArrayList;
import java.util.List;

public class ShortestPathAI implements AI{
    private BasicPathFinder pathFinder;
    private Entity origin;
    private Entity target;
    private Displacement lastDisplacement;

    public ShortestPathAI(BasicPathFinder pathFinder){
        this.pathFinder = pathFinder;
        lastDisplacement = Displacement.NOTHING;
        //this.origin = origin;
        //this.target = target;
    }

    public void setOrigin(Entity origin) {
        this.origin = origin;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public Displacement move(){
        MapRepresentation map = pathFinder.pathFinding(origin, target);

        Position position_target = map.getPositionEntity(target);
        Position position_origin = map.getPositionEntity(origin);
        List<Position> listPositions = new ArrayList<>();

        map.displayDistance(); // Show map distance from origin to target
        System.out.println(position_target == null || position_origin == null);
        System.out.println();

        if(position_target == null || position_origin == null)
            return  lastDisplacement;

        int x = (int)position_target.getX(), y = (int)position_target.getY();

        if(map.getDistance(x, y) == -1)
            return lastDisplacement;

        int[][] neighbors = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int dist = map.getDistance(x, y);
        while(map.getDistance(x, y) != 0){

            for(int[] neighbor : neighbors){
                int newX = x+neighbor[0], newY = y+neighbor[1];

                if(newX >= 0 && newY >= 0 && newX < map.getWidth() && newY < map.getHeight()){
                    int newDist = map.getDistance(newX, newY);
                    if((dist > newDist) && (newDist != -1)){
                        listPositions.add(new Position(newX, newY));
                        x = newX;
                        y = newY;
                        dist = newDist;
                        break;
                    }
                }
            }
        }

        if(listPositions.size() == 0)
            return lastDisplacement;

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
        return result;
    }
}
