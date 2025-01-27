package moteur.core_kernel;


import moteur.physics.Position;
import moteur.ui.SceneManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de controle global du jeu
 * Controle la scene, le manager d'events, les entity, les positions des entity
 */
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

    /**
     * fonction d'appel a chaque frame
     */
    public void update() throws IOException {
        updateEvents();

        updateListEntities();
        updateMovesAndListener();

        updateListEntities();
        updateEntities();

        SceneManager.getInstance().update(map);
    }

    /**
     * met a jour la liste avec toutes les entity presentent en jeu
     */
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

    /**
     * appel du manager d'event par frame
     */
    private void updateEvents() throws IOException {
        eventManager.manage();
    }

    /**
     * update des components de chaque entity
     */
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

    /**
     * Manage l'appel des fonction update de chaque entity dans la scene
     */
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

    public void setMap(Map map) {
        this.map = map;
    }
}
