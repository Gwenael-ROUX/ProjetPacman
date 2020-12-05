package moteur.core_kernel;

/**
 * Interface représentant un composant de l'entity
 */
public interface Component {
    /**
     * Fonction mettant à jour les attributs de tous les composants
     * @param entity
     */
    public void update(Entity entity);
}
