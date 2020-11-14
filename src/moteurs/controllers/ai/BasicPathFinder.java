package moteurs.controllers.ai;

import moteurs.Entity;
import moteurs.Position;
import moteurs.physics.Displacement;

import java.util.ArrayList;
import java.util.List;

public class BasicPathFinder {

    private MapRepresentation map;

    public BasicPathFinder(MapRepresentation map){
        this.map = map;
    }

    public MapRepresentation pathFinding(Entity origin, Entity target){
        Position position_origin = map.getPositionEntity(origin);

        map.resetDistance();

        int step = 0;
        boolean isFinished = false;
        int width = map.getWidth(), height = map.getHeight();
        int[][] neighbors = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        List<Position> listPositions = new ArrayList<>();
        listPositions.add(position_origin);

        if(position_origin == null)
            return map;

        map.setDistance((int) position_origin.getX(), (int) position_origin.getY(), step);

        while(! isFinished){
            List<Position> newListPositions = new ArrayList<>();
            step++;

            for(Position pos : listPositions){
                for(int[] neighbor : neighbors){
                    int x = (int) pos.getX()+neighbor[0], y = (int) pos.getY()+neighbor[1];
                    if(x < 0 || y < 0 || x >= width || y >= height) continue;
                    Entity e = map.getEntity(x, y);
                    if((e == null || e.isCrossable() || target.equals(e)) && map.getDistance(x, y) == -1){
                        newListPositions.add(new Position(x, y));
                        map.setDistance(x, y, step);

                        if(target.equals(e))
                            isFinished = true;
                    }
                }
            }
            listPositions = newListPositions;
            isFinished = isFinished || listPositions.isEmpty();
        }

        return map;
    }

    public MapRepresentation getMap(){
        return map;
    }
}
