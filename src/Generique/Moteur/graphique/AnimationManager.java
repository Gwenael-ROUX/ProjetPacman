package Generique.Moteur.graphique;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AnimationManager {
    private final HashMap<String, Animation> animations;
    private Animation currentAnimation;

    public AnimationManager() {
        animations = new HashMap<>();
    }

    public void addAnimation(String key, String folder, double duration){
        ArrayList<ImageView> list = new ArrayList<>();
        URL url = AnimationManager.class.getResource(folder);
        if (url != null) {
            try {
                File apps = new File(url.toURI());
                for (File image : Objects.requireNonNull(apps.listFiles())) {
                    ImageView im = new ImageView(new Image(AnimationManager.class.getResourceAsStream
                            (folder+"/"+image.getName())));
                    list.add(im);
                }
            } catch (URISyntaxException ignored) {
            }
            animations.put(key,new Animation(list, duration));
        }
    }

    public void setCurrentAnimation(String key){
        currentAnimation = animations.get(key);
    }

    public ImageView playAnimation(double time){
        return currentAnimation.getCurrentImage(time);
    }
}
