package moteurs.controllers.ai;

import moteurs.Position;
import moteurs.physics.Displacement;

public class PathFinder {



    public Displacement moveToward(Position targetPos, float speed){

        deltaX = targetPos.getX() - Position.getX();
        deltaY = targetPos.getX() - Position.getY();

        if (deltaX == 0 && deltaY == 0){
            return Displacement.NOTHING;
        }

        if (Math.abs(deltaX) >= Math.abs(deltaY)){
            if (deltaX > 0){
                return Displacement.RIGHT;
            }else{
                return Displacement.LEFT;
            }
        }else{
            if (deltaY > 0){
                return Displacement.DOWN;
            } else{
                return Displacement.UP;
            }
        }
    }
}
