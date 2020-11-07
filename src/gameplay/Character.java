package gameplay;

import moteurs.physics.Collider;

public abstract class Character {
    private double x, y;
    private Collider collider;

    public Character(double x, double y, Collider collider){
        this.x = x;
        this.y = y;
        this.collider = collider;
    }

    public void move(double nextX, double nextY){
        collider.move(nextX, nextY);
        x = nextX;
        y = nextY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Collider getCollider(){
        return collider;
    }
}
