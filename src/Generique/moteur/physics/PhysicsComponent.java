package Generique.moteur.physics;

import Generique.moteur.Component;
import Generique.moteur.core_kernel.Entity;

public abstract class PhysicsComponent implements ColliderListener, Component {
    public double speed;
    public Collider collider;

    public PhysicsComponent(double speed){
        this.speed = speed;
    }

    @Override
    public void update(Entity entity){
        double orientation = entity.getOrientation() % 360;
        /*if (orientation < 0 || orientation > 359){
            System.out.println("direction peut etre fausse. doit etre entre 0 et 359 degree");
        }*/
        double radDirection = Math.toRadians(orientation);

        double distanceVariation = Math.sin(radDirection);
        double angleVariation = Math.cos(radDirection);

        Position position = entity.getPosition();
        double radius =  Math.sqrt((position.getX() * position.getX()) + (position.getY() * position.getY())) + distanceVariation * speed;
        double angle  =  2 * Math.atan((position.getY() / radius) / (1 + (position.getX() / radius)));
        angle = Math.toRadians(Math.toDegrees(angle) + angleVariation);


        position.setX(radius * Math.cos(angle));
        position.setY(radius * Math.sin(angle));
        entity.setPosition(position);
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    @Override
    public abstract void onCollision(Entity entity);
}
