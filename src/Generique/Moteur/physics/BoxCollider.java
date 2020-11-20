package Generique.Moteur.physics;

import Generique.Moteur.Position;

public class BoxCollider implements Collider {
    public Position p1, p2, center;

    public BoxCollider(){

    }

    @Override
    public void move(Position newPosition) {
        // TODO
    }

    @Override
    public boolean hit(Collider collider) {
        // TODO
        return false;
    }

    @Override
    public boolean exit(Position p1, Position p2) {
        // TODO
        return false;
    }
}
