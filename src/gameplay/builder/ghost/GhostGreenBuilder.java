package gameplay.builder.ghost;

import gameplay.EntityType;
import gameplay.ai.RandomAI;
import gameplay.controller.GhostKeyboardController;
import gameplay.physics.Displacement;
import gameplay.physics.GhostPhysics;
import moteur.core_kernel.Map;
import moteur.core_kernel.builder.EntityBuilder;
import moteur.graphique.GraphicsComponent;
import moteur.physics.BoxCollider;
import moteur.physics.Position;

/**
 * Builder coresspondant au fantome vert
 */
public class GhostGreenBuilder extends EntityBuilder {
    private Map map;
    private boolean isPlayer2;

    public GhostGreenBuilder(Map map, boolean isPlayer2){
        this.map = map;
        this.isPlayer2 = isPlayer2;
    }

    @Override
    public void buildPosition(Position position) {
        // Paramètrage de l'IA
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
        if (isPlayer2)
            entity.setControllerComponent(new GhostKeyboardController(map));
        else
            entity.setControllerComponent(new RandomAI());
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + dimLong, entity.getPosition().getY() + dimLarg);

        entity.setPhysicsComponent(new GhostPhysics(1, new BoxCollider(position1, position2)));
    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent(2);
        graphicsComponent.setImage("/Image/ghost/GhostGreen.png");
        graphicsComponent.setHeight(dimLarg);
        graphicsComponent.setWidth(dimLong);
        entity.setGraphicsComponent(graphicsComponent);
    }
}
