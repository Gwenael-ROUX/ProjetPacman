package moteur.graphique;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Classe correspondant à une animation en particulier
 */
public class Animation {
    // liste des images de l'animation
    private ArrayList<Image> images;
    private double duration;

    public Animation(ArrayList<Image> images, double duration) {
        this.images = images;
        this.duration = duration;
    }

    /**
     * methode renvoyant l'image suivante en fonction de la durée de l'animation et du temps courant
     * @param time
     * @return
     */
    public Image getCurrentImage(double time){
        return images.get((int)((time % (images.size() * duration)) / duration));
    }
}
