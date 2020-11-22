package moteur.physics;

public interface Collider {
    public void update(Position newPosition);

    public boolean hit(Collider collider);

    public boolean exit(Position p1, Position p2);
}
