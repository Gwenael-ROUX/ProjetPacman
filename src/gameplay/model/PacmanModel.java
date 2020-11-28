package gameplay.model;

import gameplay.events.EventEatGum;
import gameplay.events.EventPacmanDie;
import moteur.core_kernel.EventManager;

public class PacmanModel {
    private int score, pv;

    public PacmanModel(){
        score = 0;
        pv = 3;
    }

    public void incrementPV(){
        pv++;
    }

    public void decrementPV(){
        pv--;
        System.out.println("point de vie restant : " + pv);
    }

    public boolean checkPVnull(){
        return pv <= 0 ;
    }

    public void addScore(int value){
        score += value;
        System.out.println("Score : " + score);
    }
}
