package moteur.physics;

import moteur.core_kernel.Component;
import moteur.core_kernel.Entity;

/**
 * Classe regroupant toute les propriétés physique d'une entity
 */
public abstract class PhysicsComponent implements ColliderListener, ExitListener, Component {
    public double speed;
    protected Collider collider;

    public PhysicsComponent(double speed){
        this.speed = speed;
    }

    /**
     * Met à jour la position d'une entity suivant sa vitesse et son orientation
     * @param entity
     */
    @Override
    public void update(Entity entity){
        if (entity.getOrientation() == null) {
            return;
        }
        double direction =  entity.getOrientation() % 360;
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
