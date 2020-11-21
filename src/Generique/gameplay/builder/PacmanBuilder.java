package Generique.gameplay.builder;

import Generique.gameplay.ai.ShortestPathAI;
import Generique.moteur.core_kernel.EntityBuilder;
import Generique.moteur.graphique.AnimationManager;
import Generique.moteur.graphique.GraphicsComponent;
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
        entity.setControllerComponent(new ShortestPathAI());
    }

    @Override
    public void buildPhysComp() {

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
