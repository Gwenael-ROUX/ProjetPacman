package gameplay.builder.object;

import gameplay.EntityType;
import gameplay.physics.Displacement;
import gameplay.physics.ObjectPhysics;
import moteur.core_kernel.builder.EntityBuilder;
import moteur.graphique.GraphicsComponent;
import moteur.physics.BoxCollider;
import moteur.physics.Position;

/**
 * Builder coresspondant aux gomme (sucre d'orge)
 */
public class GommeBuilder extends EntityBuilder {

    @Override
    public void buildPosition(Position position) {
        entity.setPosition(position);
    }

    @Override
    public void buildName() {
        entity.setName(EntityType.GOMME.name);
    }

    @Override
    public void buildOrientation() {
        entity.setOrientation(Displacement.NOTHING.orientation);
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX() + dimLong/4, entity.getPosition().getY() + dimLarg/4);
        Position position2 = new Position(entity.getPosition().getX() + 3*dimLong/4, entity.getPosition().getY() + 3*dimLarg/4);

        entity.setPhysicsComponent(new ObjectPhysics(new BoxCollider(position1, position2)));
    }
    
    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        // Initialisation du composant graphique
        GraphicsComponent graphicsComponent = new GraphicsComponent(0);
        graphicsComponent.setImage("/Image/object/orge.png");
        graphicsComponent.setHeight(dimLarg);
        graphicsComponent.setWidth(dimLong);
        entity.setGraphicsComponent(graphicsComponent);
    }

    @Override
    public void buildContComp() {

    }
}
