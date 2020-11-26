package moteur.graphique;

import gameplay.LevelGenerator;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimationManager {
    private final HashMap<String, Animation> animations;
    private Animation currentAnimation;

    public AnimationManager() {
        animations = new HashMap<>();
    }

    public void addAnimation(String key, String folder, double duration) {
        ArrayList<Image> list = new ArrayList<>();

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(LevelGenerator.class.getResourceAsStream(folder)));
            String line;
            while ((line = in.readLine()) != null) {
                Image im = new Image(AnimationManager.class.getResourceAsStream(line));
                list.add(im);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        animations.put(key,new Animation(list, duration));
    }

    public void setCurrentAnimation(String key){
        currentAnimation = animations.get(key);
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public Image playAnimation(double time){
        return currentAnimation.getCurrentImage(time);
    }
}
