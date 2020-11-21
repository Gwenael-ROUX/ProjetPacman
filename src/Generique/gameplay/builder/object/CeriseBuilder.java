package Generique.gameplay.builder.object;

import Generique.moteur.core_kernel.EntityBuilder;
import Generique.moteur.graphique.GraphicsComponent;
import Generique.moteur.physics.Position;

public class CeriseBuilder extends EntityBuilder {
    @Override
    public void buildPosition(Position position) {
        entity.setPosition(position);
    }

    @Override
    public void buildName() {
        entity.setName("cerise");
    }

    @Override
    public void buildOrientation() {
        entity.setOrientation(0);
    }

    @Override
    public void buildPhysComp() {

    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent();

        graphicsComponent.setImageView("/Image/object/cerise.png");
        graphicsComponent.initImagePos(entity.getPosition(), dimLong, dimLarg);

        entity.setGraphicsComponent(graphicsComponent);
    }

    @Override
    public void buildContComp() {

    }

    @Override
    public void buildSoundComp() {

    }
}
