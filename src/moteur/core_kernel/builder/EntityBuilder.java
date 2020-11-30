package moteur.core_kernel.builder;

import moteur.core_kernel.Entity;
import moteur.physics.Position;

public abstract class EntityBuilder {
    protected Entity entity;

    public Entity getEntity() {
        return entity;
    }

    public void createEntity() {
        entity = new Entity();
    }

    public abstract void buildPosition(Position position);
    public abstract void buildName();
    public abstract void buildOrientation();
    public abstract void buildContComp();
    public abstract void buildPhysComp(double dimLong, double dimLarg);
    public abstract void buildGraphComp(double dimLong, double dimLarg);
}

