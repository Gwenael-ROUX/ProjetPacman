package Generique.gameplay.builder;

import Generique.gameplay.controller.PacmanKeyboardController;
import Generique.gameplay.model.PacmanModel;
import Generique.gameplay.physics.PacmanPhysics;
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
        entity.setName("pacman");
    }

    @Override
    public void buildOrientation() {
        entity.setOrientation(0.0);
    }

    @Override
    public void buildContComp() {
        entity.setControllerComponent(new PacmanKeyboardController());
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + dimLong, entity.getPosition().getY() + dimLarg);

        entity.setPhysicsComponent(new PacmanPhysics(1, new BoxCollider(position1, position2), new PacmanModel()));
    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent();

        graphicsComponent.setImageView("/Image/pacman/pacmanRight.png");
        graphicsComponent.initImagePos(entity.getPosition(), dimLong, dimLarg);

        AnimationManager animationManager = new AnimationManager();
        double duration = 10;
        animationManager.addAnimation("up","/Animation/pacmanUp",duration);
        animationManager.addAnimation("down","/Animation/pacmanDown",duration);
        animationManager.addAnimation("left","/Animation/pacmanLeft",duration);
        animationManager.addAnimation("right","/Animation/pacmanRight",duration);

        graphicsComponent.setAnimation(new AnimationManager());
        entity.setGraphicsComponent(graphicsComponent);
    }

    @Override
    public void buildSoundComp() {

    }
}
