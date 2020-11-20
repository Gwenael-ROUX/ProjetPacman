package Generique.Moteur;

public abstract class PhysicsComponent implements ColliderListener{
    abstract public void update(Entity entity);
}
