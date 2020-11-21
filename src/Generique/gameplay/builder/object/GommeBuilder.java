package Generique.gameplay.builder.object;

import Generique.gameplay.physics.GommePhysics;
import Generique.gameplay.physics.PacmanPhysics;
import Generique.moteur.core_kernel.EntityBuilder;
import Generique.moteur.graphique.GraphicsComponent;
import Generique.moteur.physics.BoxCollider;
import Generique.moteur.physics.Position;

public class GommeBuilder extends EntityBuilder {

    @Override
    public void buildPosition(Position position) {
        entity.setPosition(position);
    }

    @Override
    public void buildName() {
        entity.setName("gomme");
    }

    @Override
    public void buildOrientation() {
        entity.setOrientation(0);
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + dimLong, entity.getPosition().getY() + dimLarg);

        entity.setPhysicsComponent(new GommePhysics(0,new BoxCollider(position1, position2)));
    }
    
    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent();

        graphicsComponent.setImageView("/Image/object/gomme.png");
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
