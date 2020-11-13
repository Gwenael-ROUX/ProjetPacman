package gameplay;

import javafx.scene.image.ImageView;
import moteurs.Position;
import moteurs.controllers.Controller;
import moteurs.physics.Collider;
import moteurs.physics.Physics;

public class EntityCharacter extends ImageEntity {
    private Controller controller;
    private Physics physics;

    public EntityCharacter() {
    }

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
}
