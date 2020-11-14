package gameplay;

import javafx.scene.image.ImageView;
import moteurs.Entity;
import moteurs.Position;
import moteurs.physics.Collider;

public class ImageEntity extends Entity {
    protected final ImageView imageView;

    public ImageEntity(Position position, Collider collider, String name, boolean isCrossable, ImageView imageView) {
        super(position, collider, name, isCrossable);
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
