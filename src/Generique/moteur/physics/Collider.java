package Generique.moteur.physics;

// TODO : suppress generic type T in future version
public interface Collider<T> {
    public void update(Position newPosition);

    public boolean hit(T collider);

    public boolean exit(Position p1, Position p2);
}
