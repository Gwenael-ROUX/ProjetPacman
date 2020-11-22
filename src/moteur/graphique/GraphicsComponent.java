package moteur.graphique;

import moteur.Component;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Timer;
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
            if (animation.getCurrentAnimation() !=  null)
                currentImage = animation.playAnimation(Timer.getInstance().getTime()*10);
        }
    }

    public void setImage(String chemin) {
        this.currentImage = new Image(AnimationManager.class.getResourceAsStream(chemin));
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public AnimationManager getAnimation() {
        return animation;
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