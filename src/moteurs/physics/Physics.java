package moteurs.physics;

public class Physics {
    private int speed;
    private int gravity;

    public Physics(int speed, int gravity){
        this.speed = speed;
        this.gravity = gravity;
    }

    public int getSpeed() {
        return speed;
    }

    public int getGravity() {
        return gravity;
    }

}
