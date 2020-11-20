package Generique.Moteur;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Animation {
    private ArrayList<ImageView> images;
    private double duration;

    public Animation(ArrayList<ImageView> images, double duration) {
        this.images = images;
        this.duration = duration;
    }

    public ImageView getCurrentImage(double time){
        return images.get((int)((time % (images.size() * duration)) / duration));
    }
}
