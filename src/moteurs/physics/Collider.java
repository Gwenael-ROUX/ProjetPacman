package moteurs.physics;import moteurs.Position;public interface Collider {    void move(Position position);    boolean hit(BoxCollider collider);    boolean exit(Position p1, Position p2);}