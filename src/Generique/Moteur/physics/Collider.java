package Generique.Moteur.physics;

public interface Collider {
    public void move(Position newPosition);

    public boolean hit(Collider collider);

    public boolean exit(Position p1, Position p2);
}
