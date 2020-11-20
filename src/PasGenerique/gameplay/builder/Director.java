package PasGenerique.gameplay.builder;

import PasGenerique.gameplay.EntityCharacter;
import PasGenerique.gameplay.ImageEntity;
import PasGenerique.moteurs.Position;
import PasGenerique.moteurs.physics.Collider;

public class Director {
    private EntityCharacterBuilder entityCharacterBuilder;
    private ImageEntityBuilder imageEntityBuilder;
    private Position position;
    private Collider collider;

    public void setEntityCharacterBuilder(EntityCharacterBuilder entity, Position position, Collider collider){
        this.entityCharacterBuilder = entity;
        init(collider, position);
    }

    public void setImageEntityBuilder(ImageEntityBuilder entity, Position position, Collider collider){
        this.imageEntityBuilder = entity;
        init(collider, position);
    }

    private void init(Collider collider, Position position){
        this.collider = collider;
        this.position = position;
    }

    public EntityCharacter getEntityCharacter() {
        return entityCharacterBuilder.getEntityCharacter();
    }

    public ImageEntity getImageEntity() {
        return imageEntityBuilder.getImageEntity();
    }

    public void constructEntityCharacter (){
        entityCharacterBuilder.createNewEntityCharacter();
        entityCharacterBuilder.buildPosition(position);
        entityCharacterBuilder.buildCollider(collider);
        entityCharacterBuilder.buildName();
        entityCharacterBuilder.buildImageView();
        entityCharacterBuilder.buildPhysics();
        entityCharacterBuilder.buildController();
        entityCharacterBuilder.buildAnimation();
    }

    public void constructImageEntity (){
        imageEntityBuilder.createNewImageEntity();
        imageEntityBuilder.buildPosition(position);
        imageEntityBuilder.buildCollider(collider);
        imageEntityBuilder.buildName();
        imageEntityBuilder.buildImageView();
        imageEntityBuilder.buildCrossable();
    }
}
