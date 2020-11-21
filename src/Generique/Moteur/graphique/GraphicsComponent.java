package Generique.Moteur.graphique;

import Generique.Moteur.Component;
import Generique.Moteur.core_kernel.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GraphicsComponent implements Component {
    private ImageView currentImage;
    private AnimationManager animation;

    public GraphicsComponent(ImageView imageView) {
        this.currentImage = imageView;
    }

    @Override
    public  void update(Entity entity){
        if (animation != null){
            animation.playAnimation(10);
        }

        //TODO bug potentiel image décallé
        currentImage.setX(entity.getPosition().getX() - currentImage.getFitWidth()/2);
        currentImage.setY(entity.getPosition().getY() - currentImage.getFitHeight()/2);
    }

    public void setImageView(String chemin) {
        this.currentImage = new ImageView(new Image(AnimationManager.class.getResourceAsStream(chemin)));
    }
}
