package moteur.graphique;

import javafx.scene.image.ImageView;
import moteur.Component;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Timer;
import javafx.scene.image.Image;

public class GraphicsComponent implements Component {
    private ImageView currentImage;
    private AnimationManager animation;
    private double height;
    private double width;

    public GraphicsComponent() {
    }

    @Override
    public  void update(Entity entity){
        if (animation != null){
            if (animation.getCurrentAnimation() !=  null) {
                Image im = animation.playAnimation(Timer.getInstance().getTime());
                currentImage.setImage(im);
            }
        }
    }

    public void setImage(String chemin) {
        this.currentImage = new ImageView(new Image(AnimationManager.class.getResourceAsStream(chemin)));
    }

    public ImageView getCurrentImage() {
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