package Generique.moteur.core_kernel;


import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Map map;
    private EventManager eventManager;
    private List<Entity> entities;

    public GameManager(Map map){
        this.map = map;
        eventManager = EventManager.getEventManager();
        this.entities = new ArrayList<>();
    }


    public void update(){
        updateEntitiesMove();

        updateEvents();

        updateCollisionAndExitListener();

        updateEntities();

        // TODO : udpate entity position in matrix map (not in loop)
    }

    private void updateEvents(){
        eventManager.manage();
    }

    private void updateEntitiesMove(){
        entities.clear();
        for(Entity entity : map){
            if(entity != null){
                entity.move();
                entities.add(entity);
            }
        }
    }

    private void updateCollisionAndExitListener(){
        for(int i = 0; i < entities.size()-1; i++){
            Entity entity1 = entities.get(i);
            if(entity1.getPhysicsComponent() != null && entity1.getPhysicsComponent().getCollider() != null){
                if(entity1.getPhysicsComponent().getCollider().exit(map.getLimitTopLeft(), map.getLimitBottomRight())){
                    entity1.getPhysicsComponent().onExit(entity1);
                }
            } else{
                continue;
            }

            for(int j = i+1; j < entities.size(); j++){
                Entity entity2 = entities.get(j);
                if(entity2.getPhysicsComponent() != null && entity2.getPhysicsComponent().getCollider() != null){
                    if(entity1.getPhysicsComponent().getCollider().hit(entity2.getPhysicsComponent().getCollider())){
                        entity1.getPhysicsComponent().onCollision(entity1, entity2);
                        entity2.getPhysicsComponent().onCollision(entity2, entity1);
                    }
                }
            }
        }
    }

    private void updateEntities(){
        for(Entity entity : map){
            if(entity != null) entity.update();
        }
    }
}
