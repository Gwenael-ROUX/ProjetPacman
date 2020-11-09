package gameplay;

import javafx.scene.image.ImageView;
import moteurs.controllers.Controller;
import moteurs.physics.Collider;
import moteurs.physics.Physics;

public class EntityCharacter extends Entity {
    private final ImageView imageView;
    private final Controller controller;
    private final Physics physics;

    public EntityCharacter(double x, double y, Collider collider, ImageView imageView, Controller controller, Physics physics) {
        super(x, y, collider);
        this.imageView = imageView;
        this.controller = controller;
        this.physics = physics;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Controller getController() {
        return controller;
    }

    public Physics getPhysics() {
        return physics;
    }

    public void update(double x, double y){
        move(x, y);

        imageView.setX(x - imageView.getFitWidth()/2);
        imageView.setY(y - imageView.getFitHeight()/2);
    }
}
