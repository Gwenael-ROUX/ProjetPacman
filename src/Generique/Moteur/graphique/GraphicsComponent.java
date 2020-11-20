package Generique.Moteur.graphique;

import Generique.Moteur.Entity;
import javafx.scene.image.ImageView;

public abstract class GraphicsComponent {
    private ImageView currentImage;
    private AnimationManager animation;

    public GraphicsComponent(ImageView imageView) {
        this.currentImage = imageView;
    }

    public  void update(Entity entity){
        if (animation != null){
            animation.playAnimation(10);
        }

        //TODO bug potentiel image décallé
        currentImage.setX(entity.getPosition().getX() - currentImage.getFitWidth()/2);
        currentImage.setY(entity.getPosition().getY() - currentImage.getFitHeight()/2);
    }

    public void setImageView(ImageView imageView) {
        this.currentImage = imageView;
    }
}
