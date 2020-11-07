package moteurs;

import moteurs.physics.BoxCollider;
import moteurs.physics.Displacement;

import java.util.TimerTask;

public class PacmanGame extends TimerTask {
    private CharacterBehaviour[] behaviours;
    private Position initMapLimit;
    private Position mapLimit;

    public PacmanGame(Position mapLimit, CharacterBehaviour[] behaviours){
        this.behaviours = behaviours;
        initMapLimit = new Position(0, 0);
        this.mapLimit = mapLimit;
    }

    @Override
    public void run() {
        Position[] prevDisp = new Position[behaviours.length];

        // Deplacements
        for(int i = 0; i < behaviours.length; i++){
            CharacterBehaviour controller = behaviours[i];
            Displacement displacement = controller.getController().move();
            double xDisp = 0, yDisp = 0;
            prevDisp[i] = new Position(controller.getCharacter().getX(), controller.getCharacter().getY());
            switch (displacement){
                case UP:
                    yDisp -= controller.getPhysics().getSpeed();
                    break;
                case DOWN:
                    yDisp += controller.getPhysics().getSpeed();
                    break;
                case LEFT:
                    xDisp -= controller.getPhysics().getSpeed();
                    break;
                case RIGHT:
                    xDisp += controller.getPhysics().getSpeed();
                    break;
                default:
                    break;
            }

            controller.update(prevDisp[i].getX()+xDisp, prevDisp[i].getY()+yDisp);
        }

        // Gestion des collisions
        // TODO : Change collision management (bug : Pacman can move on a Ghost)
        // TODO : Add collision management between Ghost and Pacman (with and without bonus)
        for(int i = 0; i < behaviours.length-1; i++){
            for(int j = i+1; j < behaviours.length; j++){
                // TODO : Suppress cast in the future
                if(behaviours[i].getCharacter().getCollider().hit((BoxCollider) behaviours[j].getCharacter().getCollider())){
                    behaviours[i].update(prevDisp[i].getX(), prevDisp[i].getY());
                    behaviours[j].update(prevDisp[j].getX(), prevDisp[j].getY());
                }
            }
        }

        // Gestion des sorties de terrains
        for(int i = 0; i < behaviours.length; i++){
            if(behaviours[i].getCharacter().getCollider().exit(initMapLimit, mapLimit)){
                behaviours[i].update(prevDisp[i].getX(), prevDisp[i].getY());
            }
        }
    }
}
