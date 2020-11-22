package Generique.gameplay.physics;

public enum Displacement {
    UP(90.0),
    DOWN(270.0),
    RIGHT(0.0),
    LEFT(180.0),
    NOTHING(null);

    public final Double orientation;

    private Displacement(Double orientation){
        this.orientation = orientation;
    }
}
