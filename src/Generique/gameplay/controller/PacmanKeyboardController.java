package Generique.gameplay.controller;

import Generique.Moteur.controller.KeyboardController;
import Generique.Moteur.core_kernel.Entity;
import Generique.gameplay.physics.Displacement;

public class PacmanKeyboardController extends KeyboardController {
    private Displacement nextMove;

    public PacmanKeyboardController(){
        nextMove = Displacement.NOTHING;
        createHandler();
    }

    private void createHandler(){
        createHandler(KeyEvent -> {
            switch (KeyEvent) {
                case Z :
                    nextMove = Displacement.UP;
                    break;
                case Q :
                    nextMove = Displacement.LEFT;
                    break;
                case S :
                    nextMove = Displacement.DOWN;
                    break;
                case D :
                    nextMove = Displacement.RIGHT;
                    break;
                default:
                    nextMove = Displacement.NOTHING;
                    break;
            }
        });
    }

    @Override
    public void update(Entity entity){
        Displacement res = nextMove;
        nextMove = Displacement.NOTHING;
        entity.setOrientation(res.orientation);
    }
}

