package Generique.Moteur.ai;

import Generique.Moteur.physics.Position;
import Generique.Moteur.core_kernel.Entity;
import Generique.Moteur.core_kernel.Map;

import java.util.Arrays;

public class MapRepresentation {

    private Map map;
    private int[][] matrixDistance;


    public MapRepresentation(Map map){
        this.map = map;
        matrixDistance = new int[map.getHeight()][map.getWidth()];
    }

    public Position getPositionEntity(Entity entity){
        if(entity == null) return null;

        for(int y = 0; y < map.getHeight(); y++){
            for(int x = 0; x < map.getWidth(); x++){
                if(entity.equals(map.getEntity(x, y)))
                    return new Position(x, y);
            }
        }
        return null;
    }

    public void setDistance(int x, int y, int dist){
        matrixDistance[y][x] = dist;
    }

    public int getDistance(int x, int y){
        return matrixDistance[y][x];
    }

    public void resetDistance(){
        for (int[] ints : matrixDistance) {
            Arrays.fill(ints, -1);
        }
    }

    public int getWidth(){
        return matrixDistance.length;
    }

    public int getHeight(){
        return matrixDistance[0].length;
    }

    public int[][] getMatrixDistance(){
        return matrixDistance;
    }

    public Map getMap(){
        return map;
    }

    public void displayDistance(){
        for(int[] dists : matrixDistance){
            for(int dist : dists){
                //System.out.print(dist + "\t");
            }
            //System.out.println();
        }
    }
}
