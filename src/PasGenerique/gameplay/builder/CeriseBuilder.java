package PasGenerique.gameplay.builder;

import PasGenerique.gameplay.LevelGenerator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import PasGenerique.moteurs.Position;
import PasGenerique.moteurs.physics.Collider;

public class CeriseBuilder extends ImageEntityBuilder {
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
        imageEntity.setName("objet");
    }

    @Override
    public void buildImageView() {
        imageEntity.setImageView(new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/cerise.png"))));
    }

    @Override
    public void buildCrossable() {
        imageEntity.setCrossable(true);
    }
}
