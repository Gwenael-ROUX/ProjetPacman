package Generique.moteur.ai;

import Generique.moteur.physics.Position;
import Generique.moteur.core_kernel.Entity;

import java.util.ArrayList;
import java.util.List;

public class BasicPathFinder {

    private MapRepresentation map;

    public BasicPathFinder(MapRepresentation map){
        this.map = map;
    }

    public List<Position> pathFinding(Entity origin, Entity target){
        Position position_origin = map.getPositionEntity(origin);
        Position position_target = map.getPositionEntity(target);

        if(position_target == null || position_origin == null)
            return  new ArrayList<>();

        map.resetDistance();

        // Compute distance
        int step = 0;
        boolean isFinished = false;
        int width = map.getWidth(), height = map.getHeight();
        int[][] neighbors = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        List<Position> listPositions = new ArrayList<>();

        listPositions.add(position_origin);
        map.setDistance((int) position_origin.getX(), (int) position_origin.getY(), step);

        while(! isFinished){
            List<Position> newListPositions = new ArrayList<>();
            step++;

            for(Position pos : listPositions){
                for(int[] neighbor : neighbors){
                    int x = (int) pos.getX()+neighbor[0], y = (int) pos.getY()+neighbor[1];
                    if(x < 0 || y < 0 || x >= width || y >= height) continue;
                    Entity e = map.getMap().getEntity(x, y);
                    //if((e == null || e.isCrossable() || target.equals(e)) && map.getDistance(x, y) == -1){
                    if((e == null || e.getPhysicsComponent() == null || target.equals(e)) && map.getDistance(x, y) == -1){
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

        //map.displayDistance(); // Show map distance from origin to target

        // Compute Path
        int x = (int)position_target.getX(), y = (int)position_target.getY();
        if(map.getDistance(x, y) == -1)
            return new ArrayList<>();

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

        return listPositions;
    }

    public MapRepresentation getMap(){
        return map;
    }
}
