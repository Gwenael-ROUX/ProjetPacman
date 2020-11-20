package PasGenerique.gameplay;

import javafx.scene.image.ImageView;
import PasGenerique.moteurs.Position;
import PasGenerique.moteurs.controllers.Controller;
import PasGenerique.moteurs.physics.Physics;

import java.util.HashMap;
import java.util.List;


public class EntityCharacter extends ImageEntity {
    private Controller controller;
    private Physics physics;
    private HashMap<String, List<ImageView>> listAnimation = new HashMap<>();


    public EntityCharacter() {}

    public void update(Position nextPosition){
        move(nextPosition);

        imageView.setX(nextPosition.getX() - imageView.getFitWidth()/2);
        imageView.setY(nextPosition.getY() - imageView.getFitHeight()/2);
    }

    public Controller getController() {
        return controller;
    }

    public Physics getPhysics() {
        return physics;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setPhysics(Physics physics) {
        this.physics = physics;
    }

    public HashMap<String, List<ImageView>> getListAnimation() {
        return listAnimation;
    }

    public void setListAnimation(HashMap<String, List<ImageView>> listAnimation) {
        this.listAnimation = listAnimation;
    }

    public void playAnimation(String state) {
        List<ImageView> animations = listAnimation.get(state);
        for (ImageView currentImage : animations) {
            imageView.setImage(currentImage.getImage());
        }
    }
}
