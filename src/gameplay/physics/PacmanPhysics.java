package gameplay.physics;

import gameplay.EntityType;
import gameplay.events.eat.EventEatCherry;
import gameplay.events.eat.EventEatGhost;
import gameplay.events.eat.EventEatGum;
import gameplay.events.eat.EventEatXMassTree;
import gameplay.model.GameModel;
import gameplay.events.mort.EventPacmanDie;
import gameplay.model.PacmanModel;
import moteur.core_kernel.Entity;
import moteur.core_kernel.EventManager;
import moteur.core_kernel.Map;
import moteur.physics.Collider;
import moteur.physics.PhysicsComponent;
import moteur.physics.Position;
import moteur.sound.SoundManager;
import java.util.HashMap;

/**
 * Classe du composant physique de pacman
 */
public class PacmanPhysics extends PhysicsComponent {
    private PacmanModel pacmanModel;
    private Map map;
    private HashMap<Entity, Boolean> ghostsEat;
    private boolean changeGhostEat;

    public PacmanPhysics(double speed, Collider collider, PacmanModel pacmanModel, Map map) {
        super(speed);
        this.collider = collider;
        this.pacmanModel = pacmanModel;
        this.map = map;
        ghostsEat = new HashMap<>();
        changeGhostEat = false;
    }

    /**
     * entrer en collision avec le box Collider du pacman
     * @param entity_owned
     * @param entity
     */
    @Override
    public void onCollision(Entity entity_owned, Entity entity){
        if(entity.getName().equals(EntityType.GHOST.name)){
            updateGhostCollision(entity_owned, entity);
        } else if(entity.getName().equals(EntityType.WALL.name)){
            updatePositionEntityPosition(entity_owned, entity);
        } else if(entity.getName().equals(EntityType.GOMME.name)){
            EventManager.getEventManager().addEvent(new EventEatGum(pacmanModel, entity, map));
        } else if(entity.getName().equals(EntityType.CADEAU.name)){
            EventManager.getEventManager().addEvent(new EventEatCherry(pacmanModel, entity, map));
        } else if(entity.getName().equals(EntityType.TREE.name)){
            EventManager.getEventManager().addEvent(new EventEatXMassTree(pacmanModel, entity, entity_owned, map));
        }
    }

    @Override
    public void onExit(Entity entity_owned){
        moveBack(entity_owned);
    }

    /**
     * met a jour les positions du pacman en fonction du controleur
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
     * Check en cas de collision avec un fantome quelconque
     * @param entity_owned
     * @param entity
     */
    private void updateGhostCollision(Entity entity_owned, Entity entity){
        if (pacmanModel.isNoel() && canEat(entity)){
            EventManager.getEventManager().addEvent(new EventEatGhost(pacmanModel, entity));
            GameModel.getInstance().resetEntity(entity);
        } else {
            resetGhostEat();
            pacmanModel.decrementPV();
            if (pacmanModel.checkPVnull()){
                if (!pacmanModel.isDead()){
                    EventManager.getEventManager().addEvent(new EventPacmanDie(pacmanModel, entity, entity_owned, map));
                }
                pacmanModel.setDead(true);
                entity_owned.setOrientation(Displacement.NOTHING.orientation);
            } else {
                GameModel.getInstance().resetGame();
                SoundManager.getInstance().addSound("touch.wav", "touch", false, 0.2f, 0L);
            }
        }
    }

    private void resetGhostEat(){
        if(pacmanModel.isNoel()) return;
        if(! changeGhostEat) return;

        ghostsEat.replaceAll((e, v) -> true);
        changeGhostEat = false;
    }

    private boolean canEat(Entity entity) {
        changeGhostEat = true;
        if(ghostsEat.containsKey(entity)){
            boolean res = ghostsEat.get(entity);
            ghostsEat.put(entity, false);
            return res;
        } else {
            ghostsEat.put(entity, false);
            return true;
        }
    }

    /**
     * mouvement de demi tour du pacman
     * @param entity_owned
     */
    private void moveBack(Entity entity_owned){
        if(entity_owned.getOrientation() == null) return;
        entity_owned.setOrientation((entity_owned.getOrientation()+180.0)%360);
        entity_owned.getPhysicsComponent().update(entity_owned);
        entity_owned.setOrientation((entity_owned.getOrientation()-180.0)%360);
    }

    /**
     * mouvement tout droit du pacman
     * @param entity_owned
     */
    private void moveFoward(Entity entity_owned){
        if(entity_owned.getOrientation() == null) return;
        entity_owned.getPhysicsComponent().update(entity_owned);
    }
}
