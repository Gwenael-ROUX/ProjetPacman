package Generique.gameplay.builder;

import Generique.gameplay.EntityType;
import Generique.gameplay.controller.PacmanKeyboardController;
import Generique.gameplay.model.PacmanModel;
import Generique.gameplay.physics.Displacement;
import Generique.gameplay.physics.PacmanPhysics;
import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.builder.EntityBuilder;
import Generique.moteur.graphique.AnimationManager;
import Generique.moteur.graphique.GraphicsComponent;
import Generique.moteur.physics.BoxCollider;
import Generique.moteur.physics.Position;

public class PacmanBuilder extends EntityBuilder {
    @Override
    public void buildPosition(Position position) {
        entity.setPosition(position);
    }

    @Override
    public void buildName() {
        entity.setName(EntityType.PACMAN.name);
    }

    @Override
    public void buildOrientation() {
        entity.setOrientation(Displacement.NOTHING.orientation);
    }

    @Override
    public void buildContComp() {
        entity.setControllerComponent(new PacmanKeyboardController());
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + dimLong, entity.getPosition().getY() + dimLarg);

        entity.setPhysicsComponent(new PacmanPhysics(5, new BoxCollider(position1, position2), new PacmanModel()));
    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent();
        graphicsComponent.setImage("/Image/pacman/pacmanRight.png");
        graphicsComponent.setHeight(dimLarg);
        graphicsComponent.setWidth(dimLong);

        AnimationManager animationManager = new AnimationManager();
        double duration = 1.0;
        animationManager.addAnimation(Displacement.UP.orientation.toString(),"/Animation/pacmanUp",duration);
        animationManager.addAnimation(Displacement.DOWN.orientation.toString(),"/Animation/pacmanDown",duration);
        animationManager.addAnimation(Displacement.LEFT.orientation.toString(),"/Animation/pacmanLeft",duration);
        animationManager.addAnimation(Displacement.RIGHT.orientation.toString(),"/Animation/pacmanRight",duration);

        graphicsComponent.setAnimation(animationManager);
        entity.setGraphicsComponent(graphicsComponent);
    }

    @Override
    public void buildSoundComp() {

    }
}
