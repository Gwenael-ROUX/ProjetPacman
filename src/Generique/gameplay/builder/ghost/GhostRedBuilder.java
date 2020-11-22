package Generique.gameplay.builder.ghost;

import Generique.gameplay.EntityType;
import Generique.gameplay.physics.Displacement;
import Generique.gameplay.physics.GhostPhysics;
import Generique.moteur.ai.AI;
import Generique.moteur.core_kernel.builder.Director;
import Generique.moteur.core_kernel.builder.EntityBuilder;
import Generique.gameplay.ai.ShortestPathAI;
import Generique.moteur.graphique.GraphicsComponent;
import Generique.moteur.physics.BoxCollider;
import Generique.moteur.physics.Position;

public class GhostRedBuilder extends EntityBuilder {
    private AI ai;

    public GhostRedBuilder(ShortestPathAI shortestPathAI) {
        ai = shortestPathAI;
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
        GraphicsComponent graphicsComponent = new GraphicsComponent();
        graphicsComponent.setImage("/Image/ghost/GhostRed.png");
        graphicsComponent.setHeight(dimLarg);
        graphicsComponent.setWidth(dimLong);
        entity.setGraphicsComponent(graphicsComponent);
    }

    @Override
    public void buildSoundComp() {

    }
}
