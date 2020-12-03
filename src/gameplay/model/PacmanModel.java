package gameplay.model;

public class PacmanModel {
    private int score, pv;
    private boolean isNoel;
    private boolean isDead;

    public PacmanModel(){
        score = 0;
        pv = 3;
        isNoel = false;
        isDead = false;
    }

    public void incrementPV(){
        pv++;
    }

    public void decrementPV(){
        if(checkPVnull()) return;
        pv--;
        System.out.println("point de vie restant : " + pv);
    }

    public int getPV(){
        return pv;
    }

    public boolean checkPVnull(){
        return pv <= 0 ;
    }

    public void addScore(int value){
        score += value;
        System.out.println("Score : " + score);
    }

    public void setNoel(boolean noel) {
        isNoel = noel;
    }

    public boolean isNoel() {
        return isNoel;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
