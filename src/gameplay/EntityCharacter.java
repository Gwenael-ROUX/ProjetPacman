package gameplay;

import javafx.scene.image.ImageView;
import moteurs.Position;
import moteurs.controllers.Controller;
import moteurs.physics.Collider;
import moteurs.physics.Physics;

public class EntityCharacter extends ImageEntity {
    private final Controller controller;
    private final Physics physics;

    public EntityCharacter(Position position, Collider collider, String name, ImageView imageView, Controller controller, Physics physics) {
        super(position, collider, name, false, imageView);
        this.controller = controller;
        this.physics = physics;
    }

    /*public EntityCharacter(EntityCharacter entityCharacter){
        super(entityCharacter.getPosition(), entityCharacter.getCollider(), entityCharacter.getName(), entityCharacter.isCrossable(), entityCharacter.getImageView());
        this.controller = entityCharacter.getController();
        this.physics = entityCharacter.getPhysics();
    }*/

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
