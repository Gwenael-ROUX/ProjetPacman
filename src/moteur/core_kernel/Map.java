package moteur.core_kernel;

import moteur.physics.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map {// implements Iterable<Entity> {
    private List<Entity>[][] matrix;
    private double dimCellHgt, dimCellWdt;
    private Position limitTopLeft, limitBottomRight;

    public Map(Position limitTopLeft, Position limitBottomRight){
        this.limitTopLeft = limitTopLeft;
        this.limitBottomRight = limitBottomRight;
    }

    public List<Entity> getEntity(int x, int y){
        return matrix[y][x];
    }

    public void addEntity(int x, int y, Entity entity){
        matrix[y][x].add(entity);
    }

    public void deleteEntity(Position position, Entity entity){
        if(position == null) return;
        matrix[(int)position.getY()][(int)position.getX()].remove(entity);
    }

    public void swap(int srcX, int srcY, int dstX, int dstY, Entity entity){
        int idx = matrix[srcY][srcX].indexOf(entity);
        if(idx != -1)
            matrix[dstY][dstX].add(matrix[srcY][srcX].remove(idx));
    }

    public Position getPositionEntity(Entity entity){
        if(entity == null) return null;

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[y].length; x++){
                for(Entity e : matrix[y][x]){
                    if(entity.equals(e))
                        return new Position(x, y);
                }
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

    public double getDimCellHgt() {
        return dimCellHgt;
    }

    public double getDimCellWdt() {
        return dimCellWdt;
    }

    public Position getLimitTopLeft() {
        return limitTopLeft;
    }

    public Position getLimitBottomRight() {
        return limitBottomRight;
    }

    public List<Entity>[][] getMatrix(){
        return matrix;
    }

    public void setMatrix(List<Entity>[][] matrix){
        this.matrix = matrix;
        this.dimCellWdt = (limitBottomRight.getX() - limitTopLeft.getX()) / getWidth();
        this.dimCellHgt = (limitBottomRight.getY() - limitTopLeft.getY()) / getHeight();
    }

    public void setLimitTopLeft(Position limitTopLeft) {
        this.limitTopLeft = limitTopLeft;
    }

    public void setLimitBottomRight(Position limitBottomRight) {
        this.limitBottomRight = limitBottomRight;
    }

    /*@Override
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
    }*/
}
