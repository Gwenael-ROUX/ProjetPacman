package gameplay.builder;

import gameplay.LevelGenerator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import moteurs.Position;
import moteurs.controllers.KeyboardController;
import moteurs.physics.Collider;
import moteurs.physics.Physics;

public class PacmanBuilder extends EntityCharacterBuilder {
    @Override
    public void buildPosition(Position position) {
        entityCharacter.setPosition(position);
    }

    @Override
    public void buildCollider(Collider collider) {
        entityCharacter.setCollider(collider);
    }

    @Override
    public void buildName() {
        entityCharacter.setName("pacman");
    }

    @Override
    public void buildImageView() {
        entityCharacter.setImageView(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/Pacman.png"))));
    }

    @Override
    public void buildController() {
        entityCharacter.setController(new KeyboardController());
    }

    @Override
    public void buildPhysics() {
        entityCharacter.setPhysics(new Physics(10, 1));
    }
}
