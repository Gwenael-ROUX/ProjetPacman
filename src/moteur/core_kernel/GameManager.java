package moteur.core_kernel;


import moteur.physics.Position;
import moteur.ui.SceneManager;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Map map;
    private EventManager eventManager;
    private List<Entity> entities;
    private List<Position> entitiesPosition;
    private boolean breakUpdate;

    public GameManager(Map map){
        this.map = map;
        eventManager = EventManager.getEventManager();
        this.entities = new ArrayList<>();
        this.entitiesPosition = new ArrayList<>();
        breakUpdate = false;
    }

    public void update(){
        breakUpdate = false;

        updateListEntities();

        if(! breakUpdate)
            updateEvents();

        if(! breakUpdate)
            updateMovesAndListener();

        if(! breakUpdate)
            updateEntities();
        if(! breakUpdate)
            SceneManager.getInstance().update(map);
    }

    private void updateListEntities(){
        entities.clear();
        entitiesPosition.clear();
        List<Entity>[][] matrix = map.getMatrix();

        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] != null && matrix[y][x].size() != 0) {
                    for(Entity e : matrix[y][x]){
                        entitiesPosition.add(new Position(x, y));
                        entities.add(e);
                    }
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

                for (Entity entity2 : entities) {
                    if ( (!entity1.equals(entity2)) && entity2.getPhysicsComponent() != null && entity2.getPhysicsComponent().getCollider() != null) {
                        if (entity2.getPhysicsComponent().getCollider().hit(entity1.getPhysicsComponent().getCollider())) {
                            entity1.getPhysicsComponent().onCollision(entity1, entity2);
                            entity2.getPhysicsComponent().onCollision(entity2, entity1);
                        }
                    }
                }
            }
        }
    }

    private void updateEntities(){
        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i) != null){
                /*if(entities.get(i).getName().equals("pacman")){
                    System.out.println(entities.get(i).getGraphicsComponent().getCurrentImage());
                }*/
                Position src_position = entitiesPosition.get(i);
                Position dst_position = entities.get(i).getPosition();
                entities.get(i).update();

                int dst_x = (int) ((dst_position.getX() + map.getDimCellWdt()/2) / map.getDimCellWdt());
                //int dst_x = (int) (dst_position.getX() / map.getDimCellWdt());
                int dst_y = (int) ((dst_position.getY() + map.getDimCellHgt()/2) / map.getDimCellHgt());
                //int dst_y = (int) (dst_position.getY() / map.getDimCellHgt());
                map.swap((int) src_position.getX(), (int) src_position.getY(), dst_x, dst_y, entities.get(i));
            }
        }
    }

    public Map getMap(){
        return map;
    }

    public void breakCurrentUpdate(){
        breakUpdate = true;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
