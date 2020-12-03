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
    private  PacmanModel pacmanModel;


    public PacmanBuilder(Map map){
        this.map = map;
        this.pacmanModel = new PacmanModel();
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
        entity.setControllerComponent(new PacmanKeyboardController(map,pacmanModel));
    }

    @Override
    public void buildPhysComp(double dimLong, double dimLarg) {
        Position position1 = new Position(entity.getPosition().getX(), entity.getPosition().getY());
        Position position2 = new Position(entity.getPosition().getX() + dimLong, entity.getPosition().getY() + dimLarg);

        entity.setPhysicsComponent(new PacmanPhysics(2, new BoxCollider(position1, position2), pacmanModel, map));
    }

    @Override
    public void buildGraphComp(double dimLong, double dimLarg) {
        GraphicsComponent graphicsComponent = new GraphicsComponent(1);
        graphicsComponent.setImage("/Image/pacman/pacmanRight.png");
        graphicsComponent.setHeight(dimLarg);
        graphicsComponent.setWidth(dimLong);

        AnimationManager animationManager = new AnimationManager();
        double duration = 0.10;
        animationManager.addAnimation(Displacement.UP.orientation.toString()+EntityType.TREE.name, "/Animation/pacnoel/pacnoelUp/init.txt",duration);
        animationManager.addAnimation(Displacement.DOWN.orientation.toString()+EntityType.TREE.name, "/Animation/pacnoel/pacnoelDown/init.txt",duration);
        animationManager.addAnimation(Displacement.LEFT.orientation.toString()+EntityType.TREE.name, "/Animation/pacnoel/pacnoelLeft/init.txt",duration);
        animationManager.addAnimation(Displacement.RIGHT.orientation.toString()+EntityType.TREE.name, "/Animation/pacnoel/pacnoelRight/init.txt",duration);

        animationManager.addAnimation(Displacement.UP.orientation.toString(), "/Animation/pacman/pacmanUp/init.txt",duration);
        animationManager.addAnimation(Displacement.DOWN.orientation.toString(), "/Animation/pacman/pacmanDown/init.txt",duration);
        animationManager.addAnimation(Displacement.LEFT.orientation.toString(), "/Animation/pacman/pacmanLeft/init.txt",duration);
        animationManager.addAnimation(Displacement.RIGHT.orientation.toString(), "/Animation/pacman/pacmanRight/init.txt",duration);

        graphicsComponent.setAnimation(animationManager);
        entity.setGraphicsComponent(graphicsComponent);
    }
}
