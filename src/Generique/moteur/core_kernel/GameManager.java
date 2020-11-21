package Generique.moteur.core_kernel;


import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Map map;
    private EventManager eventManager;
    private List<Entity> entities;


    public GameManager(Entity[][] entities){
        map = new Map(entities);
        eventManager = EventManager.getEventManager();
        this.entities = new ArrayList<>();
    }


    public void update(){
        updateEntitiesMove();

        updateEvents();

        updateCollisionListener();

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

    private void updateCollisionListener(){
        for(int i = 0; i < entities.size()-1; i++){
            Entity entity1 = entities.get(i);
            for(int j = i+1; j < entities.size(); j++){
                Entity entity2 = entities.get(j);
                if(entity1.getPhysicsComponent() != null && entity2.getPhysicsComponent() != null
                && entity1.getPhysicsComponent().getCollider() != null && entity2.getPhysicsComponent().getCollider() != null){
                    if(entity1.getPhysicsComponent().getCollider().hit(entity2.getPhysicsComponent().getCollider())){
                        entity1.getPhysicsComponent().onCollision(entity2);
                        entity2.getPhysicsComponent().onCollision(entity1);
                    }

                    // TODO : exit + exit listener
                    /*if(entity1.getPhysicsComponent().getCollider().exit()){

                    }*/
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
