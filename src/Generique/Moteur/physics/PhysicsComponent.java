package Generique.Moteur.physics;

import Generique.Moteur.Component;
import Generique.Moteur.core_kernel.Entity;

public abstract class PhysicsComponent implements ColliderListener, Component {
    public double speed;

    public PhysicsComponent(double speed){
        this.speed = speed;
    }

    @Override
    public void update(Entity entity){
        // TODO
    }

    @Override
    public abstract void onCollision(Entity entity);
}
