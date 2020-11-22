package Generique;

import Generique.gameplay.LevelGenerator;
import Generique.moteur.physics.Position;

public class Main {
    public static void main(String[] args) {
        double direction = 0;
        if (direction < 0 || direction > 359){
            System.out.println("direction peut etre fausse. doit etre entre 0 et 359 degree");
        }

        double radDirection = Math.toRadians((double)direction);

        double cosDir = Math.cos(radDirection);
        double sinDir = Math.sin(radDirection);

        double newPosX = 10 * cosDir;
        double newPosY = 10 * sinDir;

        double fx = 100 + newPosX;
        double fy = 100 + newPosY;

        System.out.println(fx);
        System.out.println(fy);
    }
}
