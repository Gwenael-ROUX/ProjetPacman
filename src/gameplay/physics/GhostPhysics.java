package gameplay.physics;

import gameplay.EntityType;
import moteur.core_kernel.Entity;
import moteur.physics.Collider;
import moteur.physics.PhysicsComponent;
import moteur.physics.Position;

/**
 * classe des composant physique des fantomes
 */
public class GhostPhysics extends PhysicsComponent {
    public GhostPhysics(double speed, Collider collider) {
        super(speed);
        this.collider = collider;
    }

    /**
     * Detection des collisions dans le box collider des fantomes
     * @param entity_owned
     * @param entity
     */
    @Override
    public void onCollision(Entity entity_owned, Entity entity){
        if (entity_owned.getOrientation() == null)
            return;

        if(entity.getName().equals(EntityType.WALL.name)){
            updatePositionEntityPosition(entity_owned, entity);
        }
    }

    /**
     * Sortie des box collider des fantomes
     * @param entity_owned
     */
    @Override
    public void onExit(Entity entity_owned){
        moveBack(entity_owned);
    }

    /**
     * mise a jour des positions des fantomes en fonction du deplacement choisi par l'IA
     * @param entity_owned
     * @param entity
     */
    private void updatePositionEntityPosition(Entity entity_owned, Entity entity){
        if(entity_owned.getOrientation() != null){
            double x = entity_owned.getPosition().getX(), y = entity_owned.getPosition().getY();
            double new_x = x, new_y = y;
            if(entity_owned.getOrientation().equals(Displacement.RIGHT.orientation)){
                new_x = x - (x+entity_owned.getGraphicsComponent().getWidth() - entity.getPosition().getX());
            } else if (entity_owned.getOrientation().equals(Displacement.LEFT.orientation)) {
                new_x = x + (entity.getPosition().getX() - x+entity_owned.getGraphicsComponent().getWidth());
            } else if (entity_owned.getOrientation().equals(Displacement.UP.orientation)) {
                new_y = y + (entity.getPosition().getY() - y+entity_owned.getGraphicsComponent().getHeight());
            } else if (entity_owned.getOrientation().equals(Displacement.DOWN.orientation)) {
                new_y = y - (y+entity_owned.getGraphicsComponent().getHeight() - entity.getPosition().getY());
            }
            entity_owned.setPosition(new Position(new_x, new_y));
            entity_owned.setOrientation(Displacement.NOTHING.orientation);
        }
    }

    /**
     * Demi tour pour les fantomes
     * @param entity_owned
     */
    private void moveBack(Entity entity_owned){
        if(entity_owned.getOrientation() == null) return;
        entity_owned.setOrientation((entity_owned.getOrientation()+180.0)%360);
        entity_owned.getPhysicsComponent().update(entity_owned);
        entity_owned.setOrientation((entity_owned.getOrientation()-180.0)%360);
    }

    /**
     * mouvement des fantomes dfoit devant eux
     * @param entity_owned
     */
    private void moveFoward(Entity entity_owned){
        if(entity_owned.getOrientation() == null) return;
        entity_owned.getPhysicsComponent().update(entity_owned);
    }
}
