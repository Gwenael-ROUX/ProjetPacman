package gameplay.builder.ghost;

import gameplay.EntityType;
import gameplay.ai.RandomAI;
import gameplay.ai.RandomShortestPathAI;
import gameplay.ai.ShortestPathAI;
import gameplay.physics.Displacement;
import gameplay.physics.GhostPhysics;
import moteur.ai.AI;
import moteur.core_kernel.builder.EntityBuilder;
import moteur.graphique.GraphicsComponent;
import moteur.physics.BoxCollider;
import moteur.physics.Position;

/**
 * Builder coresspondant au fantome bleu
 */
public class GhostBlueBuilder extends EntityBuilder {
    private AI ai;

    public GhostBlueBuilder(RandomShortestPathAI randomShortestPathAI) {
        ai = randomShortestPathAI;
    }

    @Override
    public void buildPosition(Position position) {
        entity.setPosition(position);
    }

    @Override
    public void buildName() {
        entity.setName(EntityType.GHOST.name);
    }

    @Override
    public void buildOrientation() {
        entity.setOrientation(Displacement.NOTHING.orientation);
    }

    @Override
    public void buildContComp() {
        entity.setControllerComponent(ai);
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + dimLong, entity.getPosition().getY() + dimLarg);

        entity.setPhysicsComponent(new GhostPhysics(1, new BoxCollider(position1, position2)));
    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        // Initialisation du composant graphique
        GraphicsComponent graphicsComponent = new GraphicsComponent(2);
        graphicsComponent.setImage("/Image/ghost/GhostBlue.png");
        graphicsComponent.setHeight(dimLarg);
        graphicsComponent.setWidth(dimLong);
        entity.setGraphicsComponent(graphicsComponent);
    }
}
