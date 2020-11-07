package moteurs.physics;

import moteurs.Position;

public interface Collider {
    void move(Double newPositionX, Double newPositionY);

    boolean hit(BoxCollider collider);

    boolean exit(Position p1, Position p2);
}
