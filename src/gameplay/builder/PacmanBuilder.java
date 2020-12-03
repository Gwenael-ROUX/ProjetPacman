package gameplay.builder;

import gameplay.EntityType;
import gameplay.controller.PacmanKeyboardController;
import gameplay.model.PacmanModel;
import gameplay.physics.Displacement;
import gameplay.physics.PacmanPhysics;
import moteur.core_kernel.Map;
import moteur.core_kernel.builder.EntityBuilder;
import moteur.graphique.AnimationManager;
import moteur.graphique.GraphicsComponent;
import moteur.physics.BoxCollider;
import moteur.physics.Position;

public class PacmanBuilder extends EntityBuilder {
    private Map map;

    public PacmanBuilder(Map map){
        this.map = map;
    }

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
        entity.setControllerComponent(new PacmanKeyboardController(map));
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + dimLong, entity.getPosition().getY() + dimLarg);

        entity.setPhysicsComponent(new PacmanPhysics(2, new BoxCollider(position1, position2), new PacmanModel(), map));
    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent(1);
        graphicsComponent.setImage("/Image/pacnoel/right.png");
        graphicsComponent.setHeight(dimLarg);
        graphicsComponent.setWidth(dimLong);

        AnimationManager animationManager = new AnimationManager();
        double duration = 0.10;
        animationManager.addAnimation(Displacement.UP.orientation.toString(), "/Animation/pacnoel/pacnoelUp/init.txt",duration);
        animationManager.addAnimation(Displacement.DOWN.orientation.toString(), "/Animation/pacnoel/pacnoelDown/init.txt",duration);
        animationManager.addAnimation(Displacement.LEFT.orientation.toString(), "/Animation/pacnoel/pacnoelLeft/init.txt",duration);
        animationManager.addAnimation(Displacement.RIGHT.orientation.toString(), "/Animation/pacnoel/pacnoelRight/init.txt",duration);

        graphicsComponent.setAnimation(animationManager);
        entity.setGraphicsComponent(graphicsComponent);
    }
}
