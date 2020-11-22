package PasGenerique.gameplay;

import javafx.scene.image.ImageView;
import PasGenerique.moteurs.Entity;

public class ImageEntity extends Entity {
    protected ImageView imageView;


    public ImageEntity() {}

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}