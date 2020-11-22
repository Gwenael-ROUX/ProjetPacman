package Generique.moteur.ai;

import Generique.moteur.physics.Position;
import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.Map;

import java.util.Arrays;

public class MapRepresentation {

    private Map map;
    private int[][] matrixDistance;

    public MapRepresentation(Map map){
        this.map = map;
        matrixDistance = new int[map.getHeight()][map.getWidth()];
    }

    public Position getPositionEntity(Entity entity){
        return map.getPositionEntity(entity);
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
}
