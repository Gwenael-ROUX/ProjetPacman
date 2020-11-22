package moteur.graphique;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Animation {
    private ArrayList<Image> images;
    private double duration;

    public Animation(ArrayList<Image> images, double duration) {
        this.images = images;
        this.duration = duration;
    }

    public Image getCurrentImage(double time){
        return images.get((int)((time % (images.size() * duration)) / duration));
    }
}
