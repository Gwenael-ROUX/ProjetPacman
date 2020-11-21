package Generique.Moteur.core_kernel;


public class GameManager {
    private Map map;
    private EventManager eventManager;


    public GameManager(Entity[][] entities){
        map = new Map(entities);
        eventManager = EventManager.getEventManager();
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
        for(Entity entity : map){
            if(entity != null) entity.move();
        }
    }

    private void updateCollisionListener(){
        for(Entity entity1 : map){
            if(entity1 == null) continue;
            for(Entity entity2 : map){
                if(entity2 == null) continue;
                if(! entity1.equals(entity2)){
                    entity1.getPhysicsComponent().onCollision(entity2);
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
