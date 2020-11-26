package gameplay.controller;

import gameplay.physics.Displacement;
import moteur.controller.KeyboardController;
import moteur.core_kernel.Entity;

public class GhostKeyboardController extends KeyboardController {
    private Displacement nextMove;

    public GhostKeyboardController(){
        nextMove = Displacement.NOTHING;
        createHandler();
    }

    private void createHandler(){
        createHandler(KeyEvent -> {
            switch (KeyEvent) {
                case UP :
                    nextMove = Displacement.UP;
                    break;
                case LEFT :
                    nextMove = Displacement.LEFT;
                    break;
                case DOWN :
                    nextMove = Displacement.DOWN;
                    break;
                case RIGHT :
                    nextMove = Displacement.RIGHT;
                    break;
                default:
                    //nextMove = Displacement.NOTHING;
                    break;
            }
        });
    }

    @Override
    public void update(Entity entity){
        Displacement res = nextMove;
        //nextMove = Displacement.NOTHING;

        if(res != Displacement.NOTHING)
            entity.getGraphicsComponent().getAnimation().setCurrentAnimation(res.orientation.toString());
        entity.setOrientation(res.orientation);
    }
}
