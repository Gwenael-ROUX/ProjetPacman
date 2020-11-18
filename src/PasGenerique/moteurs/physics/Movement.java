package PasGenerique.moteurs.physics;

import PasGenerique.moteurs.Position;

public class Movement {

    public static Position moveDirection(Position position, double direction, float speed){
        if (direction < 0 || direction > 359){
            System.out.println("direction peut etre fausse. doit etre entre 0 et 359 degree");
        }

        double radDirection = Math.toRadians(direction);

        double distanceVariation = Math.sin(radDirection);
        double angleVariation = Math.cos(radDirection);


        double radius =  Math.sqrt((position.getX() * position.getX()) + (position.getY() * position.getY())) + distanceVariation * speed;
        double angle  =  2 * Math.atan((position.getY() / radius) / (1 + (position.getX() / radius)));
        angle = Math.toRadians(Math.toDegrees(angle) + angleVariation);


        position.setX(radius * Math.cos(angle));
        position.setY(radius * Math.sin(angle));

        return position;
    }
}
