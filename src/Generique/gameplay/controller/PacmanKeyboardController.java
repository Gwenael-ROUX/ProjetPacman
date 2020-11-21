package Generique.gameplay.controller;

import Generique.Moteur.controller.KeyboardController;
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
    public double move(){
        Displacement res = nextMove;
        nextMove = Displacement.NOTHING;
        return res.orientation;
    }
}

