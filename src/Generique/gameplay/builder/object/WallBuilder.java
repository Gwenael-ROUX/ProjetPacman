package Generique.gameplay.builder.object;

import Generique.gameplay.EntityType;
import Generique.gameplay.physics.WallPhysics;
import Generique.moteur.core_kernel.builder.EntityBuilder;
import Generique.moteur.graphique.GraphicsComponent;
import Generique.moteur.physics.BoxCollider;
import Generique.moteur.physics.Position;

public class WallBuilder extends EntityBuilder {
    @Override
    public void buildPosition(Position position) {
        entity.setPosition(position);
    }

    @Override
    public void buildName() {
        entity.setName(EntityType.WALL.name);
    }

    @Override
    public void buildOrientation() {
        entity.setOrientation(0);
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + dimLong, entity.getPosition().getY() + dimLarg);

        entity.setPhysicsComponent(new WallPhysics(new BoxCollider(position1, position2)));
    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent();
        graphicsComponent.setImage("/Image/object/mur.png");
        graphicsComponent.setHeight(dimLarg);
        graphicsComponent.setWidth(dimLong);
        entity.setGraphicsComponent(graphicsComponent);
    }

    @Override
    public void buildContComp() {

    }

    @Override
    public void buildSoundComp() {

    }
}
