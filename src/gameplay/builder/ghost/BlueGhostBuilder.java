package gameplay.builder.ghost;

import gameplay.LevelGenerator;
import gameplay.builder.EntityCharacterBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import moteurs.Position;
import moteurs.controllers.ai.RandomAI;
import moteurs.physics.Collider;
import moteurs.physics.Physics;

public class BlueGhostBuilder extends EntityCharacterBuilder {
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
        entityCharacter.setName("ghost");
    }

    @Override
    public void buildImageView() {
        entityCharacter.setImageView(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/GhostBlue.png"))));
    }

    @Override
    public void buildController() {
        entityCharacter.setController(new RandomAI());
    }

    @Override
    public void buildPhysics() {
        entityCharacter.setPhysics(new Physics(10, 1));
    }
}
