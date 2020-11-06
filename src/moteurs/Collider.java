package moteurs;

public interface Collider {
    void move(Double newPositionX, Double newPositionY);
    Boolean hit(BoxCollider collider);
}
