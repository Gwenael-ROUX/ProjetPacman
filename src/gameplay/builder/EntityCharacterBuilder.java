package gameplay.builder;

import gameplay.EntityCharacter;
import moteurs.Position;
import moteurs.physics.Collider;

public abstract class EntityCharacterBuilder {
    protected EntityCharacter entityCharacter;

    public EntityCharacter getEntityCharacter() {
        return entityCharacter;
    }

    public void createNewEntityCharacter() {
        entityCharacter = new EntityCharacter();
    }

    public abstract void buildPosition(Position position);
    public abstract void buildCollider(Collider collider);
    public abstract void buildName();
    public abstract void buildImageView();
    public abstract void buildController();
    public abstract void buildPhysics();
}
