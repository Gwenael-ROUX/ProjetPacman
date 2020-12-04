package moteur.graphique;

import javafx.scene.image.Image;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * classe gérant les différentes animations d'une entity
 */
public class AnimationManager {
    // HashMap regroupant les différetes animations d'une entity
    private final HashMap<String, Animation> animations;
    // Animation courante
    private Animation currentAnimation;

    public AnimationManager() {
        animations = new HashMap<>();
    }

    /**
     * Fonction ajoutant une animation à la hashmap (on lui donne le chemin d'un .txt regroupant
     * le chemin des différentes images de l'animation)
     * @param key
     * @param init
     * @param duration
     */
    public void addAnimation(String key, String init, double duration) {
        ArrayList<Image> list = new ArrayList<>();

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(AnimationManager.class.getResourceAsStream(init)));
            String line;
            while ((line = in.readLine()) != null) {
                Image im = new Image(AnimationManager.class.getResourceAsStream(line));
                list.add(im);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        // ajoute l'Animation à la hashmap
        animations.put(key,new Animation(list, duration));
    }

    /**
     * Définis l'animation courante
     * @param key
     */
    public void setCurrentAnimation(String key){
        currentAnimation = animations.get(key);
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    /**
     * fonction demandant la prochaine image de l'animation
     */
    public Image playAnimation(double time){
        return currentAnimation.getCurrentImage(time);
    }
}
