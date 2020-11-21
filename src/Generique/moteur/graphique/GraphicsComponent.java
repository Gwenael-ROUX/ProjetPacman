package Generique.moteur.graphique;

import Generique.moteur.Component;
import Generique.moteur.core_kernel.Entity;
import Generique.moteur.physics.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicsComponent implements Component {
    private ImageView currentImage;
    private AnimationManager animation;

    public GraphicsComponent() {
    }

    @Override
    public  void update(Entity entity){
        if (animation != null){
            //TODO mettre le time courant
            animation.playAnimation(10);
        }

        //TODO bug potentiel image décallé
        currentImage.setX(entity.getPosition().getX() - currentImage.getFitWidth()/2);
        currentImage.setY(entity.getPosition().getY() - currentImage.getFitHeight()/2);
    }

    public void setImageView(String chemin) {
        this.currentImage = new ImageView(new Image(AnimationManager.class.getResourceAsStream(chemin)));
    }

    public void setAnimation(AnimationManager animation) {
        this.animation = animation;
    }

    public void initImagePos(Position position, double dimCaseLong, double dimCaseLarg){
        currentImage.setFitHeight(dimCaseLarg);
        currentImage.setFitWidth(dimCaseLong);
        currentImage.setX(position.getX());
        currentImage.setY(position.getY());
    }
}
