package moteurs;

import javafx.scene.image.ImageView;
import gameplay.Character;
import moteurs.controllers.Controller;
import moteurs.physics.Physics;

public class CharacterBehaviour {
    private Character character;
    private ImageView img;
    private Controller controller;
    private Physics physics;

    public CharacterBehaviour(Character character, ImageView img, Controller controller, Physics physics){
        this.character = character;
        this.img = img;
        this.controller = controller;
        this.physics = physics;
    }

    public void update(double x, double y){
        character.move(x, y);

        img.setX(x - img.getFitWidth()/2);
        img.setY(y - img.getFitHeight()/2);
    }

    public Controller getController(){
        return controller;
    }

    public Physics getPhysics(){
        return physics;
    }

    public Character getCharacter(){
        return character;
    }
}
