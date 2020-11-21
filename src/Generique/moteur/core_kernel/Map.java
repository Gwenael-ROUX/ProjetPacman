package Generique.moteur.core_kernel;

import Generique.moteur.physics.Position;

import java.util.Iterator;

public class Map implements Iterable<Entity> {
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


    @Override
    public Iterator<Entity> iterator() {
        return null;
    }

    private class MapIterator implements Iterator<Entity>{
        private int row = 0, col = 0;

        @Override
        public boolean hasNext() {
            if(matrix.length == 0)
                return false;

            if (col < matrix.length && row < matrix[col].length){
                return true;
            } else if(col+1 < matrix.length){
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Entity next() {
            if (this.hasNext()){
                if(row == matrix[col].length){
                    col++;
                    row = 0;
                }
                return matrix[col][row++];
            } else{
                return null;
            }
        }
    }
}
