package moteur.core_kernel.builder;

import moteur.core_kernel.Entity;
import moteur.physics.Position;

/**
 * class abstraite servant de modèle à tout les builder
 */
public abstract class EntityBuilder {
    protected Entity entity;

    public Entity getEntity() {
        return entity;
    }

    /**
     * renvoie une nouvelle entity vide
     */
    public void createEntity() {
        entity = new Entity();
    }

    /**
     * Paramètrage de la position de l'entity
     * @param position
     */
    public abstract void buildPosition(Position position);

    /**
     * Paramètrage du nom de l'entity
     */
    public abstract void buildName();

    /**
     * Paramètrage de l'orientation de l'entity
     */
    public abstract void buildOrientation();

    /**
     * Paramètrage du controller de l'entity
     */
    public abstract void buildContComp();

    /**
     * Paramètrage de la composante physique de l'entity
     * @param dimLong
     * @param dimLarg
     */
    public abstract void buildPhysComp(double dimLong, double dimLarg);

    /**
     * Paramètrage de la composante graphique de l'entity
     * @param dimLong
     * @param dimLarg
     */
    public abstract void buildGraphComp(double dimLong, double dimLarg);
}

