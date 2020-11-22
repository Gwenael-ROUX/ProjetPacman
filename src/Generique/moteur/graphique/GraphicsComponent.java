package Generique.moteur.graphique;

import Generique.moteur.Component;
import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.Timer;
import javafx.scene.image.Image;

public class GraphicsComponent implements Component {
    private Image currentImage;
    private AnimationManager animation;
    private double height;
    private double width;

    public GraphicsComponent() {
    }

    @Override
    public  void update(Entity entity){
        if (animation != null){
            animation.playAnimation(Timer.getInstance().getTime());
        }
    }

    public void setImage(String chemin) {
        this.currentImage = new Image(AnimationManager.class.getResourceAsStream(chemin));
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setAnimation(AnimationManager animation) {
        this.animation = animation;
    }
}