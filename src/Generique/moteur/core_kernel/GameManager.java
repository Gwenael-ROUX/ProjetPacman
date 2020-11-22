package Generique.moteur.core_kernel;


import Generique.moteur.physics.Position;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Map map;
    private EventManager eventManager;
    private List<Entity> entities;
    private List<Position> entitiesPosition;

    public GameManager(Map map){
        this.map = map;
        eventManager = EventManager.getEventManager();
        this.entities = new ArrayList<>();
        this.entitiesPosition = new ArrayList<>();
    }


    public void update(){
        updateListEntities();

        updateEvents();

        updateMovesAndListener();

        updateEntities();
    }

    private void updateListEntities(){
        entities.clear();
        entitiesPosition.clear();
        Entity[][] matrix = map.getMatrix();

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] != null) {
                    entitiesPosition.add(new Position(x, y));
                    entities.add(matrix[y][x]);
                }
            }
        }
    }

    private void updateEvents(){
        eventManager.manage();
    }

    private void updateMovesAndListener(){
        for(int i = 0; i < entities.size()-1; i++){
            Entity entity1 = entities.get(i);
            if(entity1.getPhysicsComponent() != null && entity1.getPhysicsComponent().getCollider() != null){
                entity1.move();
                if(entity1.getPhysicsComponent().getCollider().exit(map.getLimitTopLeft(), map.getLimitBottomRight())){
                    entity1.getPhysicsComponent().onExit(entity1);
                }
            } else{
                continue;
            }

            for (Entity entity2 : entities) {
                if (entity2.getPhysicsComponent() != null && entity2.getPhysicsComponent().getCollider() != null) {
                    if (entity1.getPhysicsComponent().getCollider().hit(entity2.getPhysicsComponent().getCollider())) {
                        entity1.getPhysicsComponent().onCollision(entity1, entity2);
                        entity2.getPhysicsComponent().onCollision(entity2, entity1);
                    }
                }
            }
        }
    }

    private void updateEntities(){
        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i) != null){
                Position src_position = entitiesPosition.get(i);
                Position dst_position = entities.get(i).getPosition();
                entities.get(i).update();

                map.swap((int) src_position.getX(), (int) src_position.getY(), (int) (dst_position.getX()/map.getDimCellWdt()), (int) (dst_position.getY()/map.getDimCellHgt()));
            }
        }
    }
}
