package Generique.Moteur.physics;

import Generique.Moteur.Position;

public interface Collider {
    public void move(Position newPosition);

    public boolean hit(Collider collider);

    public boolean exit(Position p1, Position p2);
}
