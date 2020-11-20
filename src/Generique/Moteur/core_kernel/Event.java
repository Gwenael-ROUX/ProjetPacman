package Generique.Moteur.core_kernel;

public abstract class Event {
    protected Entity entity;

    public abstract void handle();
}
