package Generique.Moteur.core_kernel;

import Generique.Moteur.physics.Position;

public class Map {
    private Entity[][] matrix;


    public Map(Entity[][] matrix){
        this.matrix = matrix;
    }

    public Entity getEntity(int x, int y){
        return matrix[y][x];
    }

    public void setEntity(int x, int y, Entity entity){
        matrix[y][x] = entity;
    }

    public void swap(int srcX, int srcY, int dstX, int dstY){
        Entity e = matrix[dstY][dstX];
        matrix[dstY][dstX] = matrix[srcY][srcX];
        matrix[srcY][srcX] = e;
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

    public int getWidth(){
        return matrix[0].length;
    }

    public int getHeight(){
        return matrix.length;
    }
}
