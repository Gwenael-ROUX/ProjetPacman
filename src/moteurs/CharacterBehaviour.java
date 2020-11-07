package moteurs;

import javafx.scene.image.ImageView;
import gameplay.Entity;
import moteurs.controllers.Controller;
import moteurs.physics.Physics;

public class CharacterBehaviour {
    private Entity entity;
    private ImageView img;
    private Controller controller;
    private Physics physics;

    public CharacterBehaviour(Entity entity, ImageView img, Controller controller, Physics physics){
        this.entity = entity;
        this.img = img;
        this.controller = controller;
        this.physics = physics;
    }

    public void update(double x, double y){
        entity.move(x, y);

        img.setX(x - img.getFitWidth()/2);
        img.setY(y - img.getFitHeight()/2);
    }

    public Controller getController(){
        return controller;
    }

    public Physics getPhysics(){
        return physics;
    }

    public Entity getCharacter(){
        return entity;
    }
}
