package gameplay.builder;

import gameplay.LevelGenerator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import moteurs.Position;
import moteurs.physics.Collider;

public class MurBuilder extends ImageEntityBuilder{
    @Override
    public void buildPosition(Position position) {
        imageEntity.setPosition(position);
    }

    @Override
    public void buildCollider(Collider collider) {
        imageEntity.setCollider(collider);
    }

    @Override
    public void buildName() {
        imageEntity.setName("mur");
    }

    @Override
    public void buildImageView() {
        imageEntity.setImageView(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/mur.png"))));
    }
}
