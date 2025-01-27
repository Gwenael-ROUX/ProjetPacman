package moteur.ai;

import moteur.physics.Position;
import moteur.core_kernel.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour recherche du plus court chemin
 */
public class BasicPathFinder {
    private MapRepresentation map;
    private List<String> crossableEntitiesNames;

    /*public BasicPathFinder(MapRepresentation map, List<String> crossableEntitiesNames){
        this.map = map;
        this.crossableEntitiesNames = crossableEntitiesNames;
    }*/

    public BasicPathFinder(List<String> crossableEntitiesNames){
        this.crossableEntitiesNames = crossableEntitiesNames;
    }

    public void setMap(MapRepresentation map){
        this.map = map;
    }

    /**
     * Algorithme de plus court chemin a travers les colliders
     * @param origin position initial du NPC
     * @param target position de l'objectif du NPC
     * @return List position du critical path
     */
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
        //recherche tant que position finale non determinee
        while(! isFinished){
            List<Position> newListPositions = new ArrayList<>();
            step++;

            for(Position pos : listPositions){
                for(int[] neighbor : neighbors){
                    int x = (int) pos.getX()+neighbor[0], y = (int) pos.getY()+neighbor[1];
                    if(x < 0 || y < 0 || x >= width || y >= height) continue;

                    if((map.getMap().getEntity(x, y) == null || map.getMap().getEntity(x, y).size() == 0) && map.getDistance(x, y) == -1){
                        newListPositions.add(new Position(x, y));
                        map.setDistance(x, y, step);
                    } else{
                        int dist = -1;
                        for(Entity e : map.getMap().getEntity(x, y)){
                            //if((e == null || e.isCrossable() || target.equals(e)) && map.getDistance(x, y) == -1){
                            if((e == null || e.getPhysicsComponent() == null || target.equals(e) || isCrossable(e)) && map.getDistance(x, y) == -1){
                                dist = step;

                                if(target.equals(e))
                                    isFinished = true;
                            } else if(!isFinished && e != null && !isCrossable(e)){
                                dist = -1;
                                break;
                            }
                        }
                        if(dist >= 0){
                            newListPositions.add(new Position(x, y));
                            map.setDistance(x, y, dist);
                        }
                    }
                }
            }
            listPositions = newListPositions;
            isFinished = isFinished || listPositions.isEmpty();
        }

        //map.displayDistance(); // Show map distance from origin to target

        // Compute Path
        int x = (int)position_target.getX(), y = (int)position_target.getY();
        listPositions = new ArrayList<>();
        listPositions.add(position_target);
        
        if(map.getDistance(x, y) == -1)
            return new ArrayList<>();

        int dist = map.getDistance(x, y);
        while(map.getDistance(x, y) > 0){

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

    /**
     * check si une entity peut etre traverser
     * @param entity entity a checker
     * @return si l'entity est traversable
     */
    private boolean isCrossable(Entity entity){
        String entity_name = entity.getName();
        for(String name : crossableEntitiesNames){
            if(entity_name.equals(name))
                return true;
        }
        return false;
    }

    public MapRepresentation getMap(){
        return map;
    }
}
