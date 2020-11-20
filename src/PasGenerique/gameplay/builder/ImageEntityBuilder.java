package PasGenerique.gameplay.builder;

import PasGenerique.gameplay.ImageEntity;
import PasGenerique.moteurs.Position;
import PasGenerique.moteurs.physics.Collider;

public abstract class ImageEntityBuilder {
    protected ImageEntity imageEntity;

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void createNewImageEntity() {
        imageEntity = new ImageEntity();
    }

    public abstract void buildPosition(Position position);
    public abstract void buildCollider(Collider collider);
    public abstract void buildName();
    public abstract void buildImageView();
    public abstract void buildCrossable();

}
