package moteur.physics;

/**
 * Interface definissant les classes permettant de detecter les colisions et sortie de zone
 */
public interface Collider {
    /**
     * Met à jour la position du collider
     * @param newPosition
     */
    public void update(Position newPosition);

    /**
     * Fonction permettant de detecter si il y a une collision avec le collider passé en paramètre
     * @param collider
     * @return
     */
    public boolean hit(Collider collider);

    /**
     * Fonction détectant si le collider est en dehors du rectangle décris par les deux positions passées en paramètres
     * @param p1
     * @param p2
     * @return
     */
    public boolean exit(Position p1, Position p2);
}
