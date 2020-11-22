package Generique.moteur.graphique;

import Generique.moteur.Component;
import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.Timer;
import Generique.moteur.physics.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicsComponent implements Component {
    private Image currentImage;
    private AnimationManager animation;
    private double height;
    private double weight;

    public GraphicsComponent() {
    }

    @Override
    public  void update(Entity entity){
        if (animation != null){
            animation.playAnimation(Timer.getInstance().getTime());
        }

        //TODO bug potentiel image décallé
        //currentImage.setX(entity.getPosition().getX() - currentImage.getFitWidth()/2);
        //currentImage.setY(entity.getPosition().getY() - currentImage.getFitHeight()/2);
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

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setAnimation(AnimationManager animation) {
        this.animation = animation;
    }

//    public void initImagePos(Position position, double dimCaseLong, double dimCaseLarg){
//        currentImage.setFitHeight(dimCaseLarg);
        //currentImage.setFitWidth(dimCaseLong);
        //currentImage.setX(position.getX());
        //currentImage.setY(position.getY());
 //   }
}