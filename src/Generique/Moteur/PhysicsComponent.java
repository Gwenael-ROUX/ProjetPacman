package Generique.Moteur;

import Generique.Moteur.core_kernel.Entity;

public abstract class PhysicsComponent implements ColliderListener{
    abstract public void update(Entity entity);
}
