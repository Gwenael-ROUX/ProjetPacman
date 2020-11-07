package gameplay;

import moteurs.physics.Collider;

public class Pacman extends Character{
    private double x, y;
    private Collider collider;

    public Pacman(double x, double y, Collider collider){
        super(x, y, collider);
    }
}
