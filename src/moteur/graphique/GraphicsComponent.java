package moteur.graphique;

import javafx.scene.image.ImageView;
import moteur.core_kernel.Component;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Timer;
import javafx.scene.image.Image;

/**
 * Classe correspondant au composant graphique d'une entity
 */
public class GraphicsComponent implements Component {
    private ImageView currentImage;
    // Définis dans les builders si c'est un objet animé
    private AnimationManager animation;
    // Correspondant au numéro de la couche
    private Integer layer;
    private double height;
    private double width;

    public GraphicsComponent(Integer layer) {
        this.layer = layer;
    }

    @Override
    public  void update(Entity entity){
        if (animation != null){
            if (animation.getCurrentAnimation() !=  null) {
                Image im = animation.playAnimation(Timer.getInstance().getTime());
                currentImage.setImage(im);
            }
        }
        if (currentImage != null) {
            currentImage.setLayoutX(entity.getPosition().getX());
            currentImage.setLayoutY(entity.getPosition().getY());
        }
    }

    public void setImage(String chemin) {
        if (chemin == null)
            currentImage = null;
        else
            this.currentImage = new ImageView(new Image(GraphicsComponent.class.getResourceAsStream(chemin)));
    }

    public ImageView getCurrentImage() {
        return currentImage;
    }

    public AnimationManager getAnimationManager() {
        return animation;
    }

    public void setHeight(double height) {
        this.height = height;
        currentImage.setFitHeight(height);
    }

    public void setWidth(double width) {
        this.width = width;
        currentImage.setFitWidth(width);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setAnimationManager(AnimationManager animation) {
        this.animation = animation;
    }

    public void setCurrentImage(ImageView currentImage) {
        this.currentImage = currentImage;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }
}