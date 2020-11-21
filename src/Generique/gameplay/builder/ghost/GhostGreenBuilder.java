package Generique.gameplay.builder.ghost;

import Generique.moteur.core_kernel.EntityBuilder;
import Generique.gameplay.ai.ShortestPathAI;
import Generique.moteur.graphique.GraphicsComponent;
import Generique.moteur.physics.Position;

public class GhostGreenBuilder extends EntityBuilder {
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
    public void buildPhysComp() {

    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent();
        graphicsComponent.setImageView("/Image/ghost/GhostGreen.png");
        entity.setGraphicsComponent(graphicsComponent);
    }

    @Override
    public void buildSoundComp() {

    }
}
