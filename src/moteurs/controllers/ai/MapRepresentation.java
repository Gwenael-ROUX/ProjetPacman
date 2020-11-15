package moteurs.controllers.ai;

import gameplay.EntityCharacter;
import moteurs.Entity;
import moteurs.Position;

import java.util.Arrays;

public class MapRepresentation {

    private Entity[][] matrix;
    private int[][] matrixDistance;
    private boolean updated;


    public MapRepresentation(Entity[][] matrix){
        this.matrix = matrix;
    }

    public void update(Entity[][] matrix){
        this.matrix = matrix;
        matrixDistance = new int[matrix.length][matrix[0].length];
    }

    public Position getPositionEntity(Entity entity){
        if(entity == null) return null;

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[y].length; x++){
                if(entity.equals(matrix[y][x]))
                    return new Position(x, y);
            }
        }
        return null;
    }

    public Entity getEntity(int x, int y){
        return matrix[y][x];
    }

    public void setDistance(int x, int y, int dist){
        matrixDistance[y][x] = dist;
    }

    public void setEntity(int x, int y, Entity entity){
        matrix[y][x] = entity;
    }

    public void swap(int srcX, int srcY, int dstX, int dstY){
        Entity e = matrix[dstY][dstX];
        matrix[dstY][dstX] = matrix[srcY][srcX];
        matrix[srcY][srcX] = e;
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
        return matrix[0].length;
    }

    public int getHeight(){
        return matrix.length;
    }

    public int[][] getMatrixDistance(){
        return matrixDistance;
    }

    public Entity[][] getMatrix(){
        return matrix;
    }

    public boolean isUpdated(){
        return updated;
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
