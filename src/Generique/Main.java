package Generique;

import Generique.Moteur.AnimationManager;

public class Main {
    public static void main(String[] args) {
        AnimationManager animation = new AnimationManager();
        animation.addAnimation("theolebg", "/Image/pacman", 20);
        animation.setCurrentAnimation("theolebg");

        for (int i = 0; i < 200000; i++) {
            System.out.println(animation.playAnimation(i));
        }
    }
}
