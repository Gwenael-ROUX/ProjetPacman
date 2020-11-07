package gameplay;

import moteurs.physics.Collider;

public class Ghost extends Character{
    private double x, y;
    private Collider collider;

    public Ghost(double x, double y, Collider collider){
        super(x, y, collider);
    }
}
