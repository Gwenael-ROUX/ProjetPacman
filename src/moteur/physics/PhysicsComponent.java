package moteur.physics;

import moteur.Component;
import moteur.core_kernel.Entity;

public abstract class PhysicsComponent implements ColliderListener, ExitListener, Component {
    public double speed;
    protected Collider collider;

    public PhysicsComponent(double speed){
        this.speed = speed;
    }

    @Override
    public void update(Entity entity){
        if (entity.getOrientation() == null) {
            return;
        }
        double direction =  entity.getOrientation() % 360;
//        if (direction < 0 || direction > 359){
//            System.out.println("direction peut etre fausse. doit etre entre 0 et 359 degree");
//        }

        double radDirection = Math.toRadians((double)direction);

        double cosDir = Math.cos(radDirection);
        double sinDir = Math.sin(radDirection);

        double newPosX = speed * cosDir;
        double newPosY = speed * sinDir;

        double fx = entity.getPosition().getX() + newPosX;
        double fy = entity.getPosition().getY() + newPosY;

        entity.getPosition().setX(fx);
        entity.getPosition().setY(fy);
        collider.update(entity.getPosition());
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    public Collider getCollider() {
        return collider;
    }

    public double getSpeed(){
        return speed;
    }

    @Override
    public void onCollision(Entity entity_owned, Entity entity){}

    @Override
    public void onExit(Entity entity_owned){}
}
