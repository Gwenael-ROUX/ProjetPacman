package gameplay.physics;

import gameplay.EntityType;
import gameplay.PacmanGame;
import gameplay.events.*;
import gameplay.events.animation.EventPacmanDie;
import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.EventManager;
import moteur.core_kernel.GameManager;
import moteur.core_kernel.Map;
import moteur.physics.Collider;
import moteur.physics.PhysicsComponent;
import moteur.physics.Position;
import moteur.ui.SceneManager;

public class PacmanPhysics extends PhysicsComponent {
    private PacmanModel pacmanModel;
    private Map map;

    public PacmanPhysics(double speed, Collider collider, PacmanModel pacmanModel, Map map) {
        super(speed);
        this.collider = collider;
        this.pacmanModel = pacmanModel;
        this.map = map;
    }

    @Override
    public void onCollision(Entity entity_owned, Entity entity){
        if(entity.getName().equals(EntityType.GHOST.name)){
            updateGhostCollision(entity_owned, entity);
        } else if(entity.getName().equals(EntityType.WALL.name)){
            updatePositionEntityPosition(entity_owned, entity);
        } else if(entity.getName().equals(EntityType.GOMME.name)){
            EventManager.getEventManager().addEvent(new EventEatGum(pacmanModel, entity, map));
        } else if(entity.getName().equals(EntityType.CERISE.name)){
            EventManager.getEventManager().addEvent(new EventEatCherry(pacmanModel, entity, map));
        } else if(entity.getName().equals(EntityType.TREE.name)){
            EventManager.getEventManager().addEvent(new EventEatXMassTree(pacmanModel, entity, entity_owned, map));
        }
    }

    @Override
    public void onExit(Entity entity_owned){
        moveBack(entity_owned);
    }

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

    private void updateGhostCollision(Entity entity_owned, Entity entity){
        if (pacmanModel.isNoel()){
            EventManager.getEventManager().addEvent(new EventEatGhost(pacmanModel, entity));
            PacmanGame.getGame().resetEntity(entity);
        } else {
            pacmanModel.decrementPV();
            if (pacmanModel.checkPVnull()){
                if (!pacmanModel.isDead()){
                    EventManager.getEventManager().addEvent(new EventPacmanDie(pacmanModel, entity, entity_owned, map));
                }
                pacmanModel.setDead(true);
                entity_owned.setOrientation(Displacement.NOTHING.orientation);
            } else {
                SceneManager.getInstance().reset();
            }
        }
    }

    private void moveBack(Entity entity_owned){
        if(entity_owned.getOrientation() == null) return;
        entity_owned.setOrientation((entity_owned.getOrientation()+180.0)%360);
        entity_owned.getPhysicsComponent().update(entity_owned);
        entity_owned.setOrientation((entity_owned.getOrientation()-180.0)%360);
    }
}
