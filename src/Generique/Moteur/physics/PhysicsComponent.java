package Generique.Moteur.physics;

import Generique.Moteur.core_kernel.Entity;

public abstract class PhysicsComponent implements ColliderListener{
    public double speed;

    public PhysicsComponent(double speed){
        this.speed = speed;
    }

    public void update(Entity entity){
        // TODO
    }

    @Override
    public abstract void onCollision(Entity entity);
}
