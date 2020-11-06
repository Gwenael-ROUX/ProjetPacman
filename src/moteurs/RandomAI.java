package moteurs;

public class RandomAI implements AI {

    private double x, y;

    public RandomAI(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Displacement move(){
        Displacement[] possibleDisplacement = new Displacement[]{Displacement.RIGHT, Displacement.LEFT, Displacement.UP, Displacement.DOWN, Displacement.NOTHING};
        int choose = (int) (Math.random()*possibleDisplacement.length);
        //updatae de la physique ??? (et des nouvelles coordonnées)

        return possibleDisplacement[choose];
    }
}
