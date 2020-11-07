package moteurs.physics;

public class Physics {
    private int speed;
    private int gravity;
    private boolean collision;

    public Physics(int speed, int gravity, boolean collision){
        this.speed = speed;
        this.gravity = gravity;
        this.collision = collision;
    }

    public int getSpeed() {
        return speed;
    }

    public int getGravity() {
        return gravity;
    }

    public boolean isCollision() {
        return collision;
    }
}
