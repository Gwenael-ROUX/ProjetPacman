package Generique;

import Generique.Moteur.graphique.AnimationManager;

public class Main {
    public static void main(String[] args) {
        AnimationManager animation = new AnimationManager();
        animation.addAnimation("theolebg", "/Image/pacman", 10);
        animation.setCurrentAnimation("theolebg");

        for (int i = 0; i < 200000; i++) {
            System.out.println(animation.playAnimation(i));
        }
    }
}
