package gameplay;

import javafx.scene.image.ImageView;
import moteurs.Entity;
import moteurs.Position;
import moteurs.controllers.Controller;
import moteurs.physics.Collider;
import moteurs.physics.Physics;

public class EntityCharacter extends Entity {
    private final ImageView imageView;
    private final Controller controller;
    private final Physics physics;

    public EntityCharacter(Position position, Collider collider, ImageView imageView, Controller controller, Physics physics) {
        super(position, collider);
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

    public void update(Position nextPosition){
        move(nextPosition);

        imageView.setX(nextPosition.getX() - imageView.getFitWidth()/2);
        imageView.setY(nextPosition.getY() - imageView.getFitHeight()/2);
    }
}
