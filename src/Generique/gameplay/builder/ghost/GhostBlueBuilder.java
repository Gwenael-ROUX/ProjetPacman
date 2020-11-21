package Generique.gameplay.builder.ghost;

import Generique.gameplay.physics.PacmanPhysics;
import Generique.moteur.core_kernel.EntityBuilder;
import Generique.gameplay.ai.ShortestPathAI;
import Generique.moteur.graphique.GraphicsComponent;
import Generique.moteur.physics.BoxCollider;
import Generique.moteur.physics.Position;

public class GhostBlueBuilder extends EntityBuilder {
    @Override
    public void buildPosition(Position position) {
        entity.setPosition(position);
    }

    @Override
    public void buildName() {
        entity.setName("ghost");
    }

    @Override
    public void buildOrientation() {
        entity.setOrientation(0.0);
    }

    @Override
    public void buildContComp() {
        entity.setControllerComponent(new ShortestPathAI());
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + dimLong, entity.getPosition().getY() + dimLarg);

        entity.setPhysicsComponent(new PacmanPhysics(1,new BoxCollider(position1, position2)));
    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent();
        graphicsComponent.setImageView("/Image/ghost/GhostBlue.png");
        entity.setGraphicsComponent(graphicsComponent);
    }

    @Override
    public void buildSoundComp() {

    }
}
