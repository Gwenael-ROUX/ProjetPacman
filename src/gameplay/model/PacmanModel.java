package gameplay.model;

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
    }

    public void addScore(int value){
        score += value;
        //System.out.println("Score : " + score);
    }
}
