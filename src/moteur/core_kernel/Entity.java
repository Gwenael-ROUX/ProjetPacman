package moteur.core_kernel;

import moteur.physics.PhysicsComponent;
import moteur.physics.Position;
import moteur.controller.ControllerComponent;
import moteur.graphique.GraphicsComponent;

/**
 * Classe des objets de jeu
 */
public class Entity {
    private Position position;
    private String name;
    private Double orientation;
    private ControllerComponent controllerComponent;
    private PhysicsComponent physicsComponent;
    private GraphicsComponent graphicsComponent;

    public Entity() {
    }

    /**
     * movement d'entity par Input Controlleur
     */
    public void move(){
        if(controllerComponent != null)
            controllerComponent.update(this);
        if (physicsComponent != null)
            physicsComponent.update(this);
    }

    /**
     * Function appele chaque frame
     * update de l'affichage du l'entity
     */
    public void update(){
        if (graphicsComponent != null)
            graphicsComponent.update(this);
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setControllerComponent(ControllerComponent controllerComponent) {
        this.controllerComponent = controllerComponent;
    }

    public void setPhysicsComponent(PhysicsComponent physicsComponent) {
        this.physicsComponent = physicsComponent;
    }

    public void setGraphicsComponent(GraphicsComponent graphicsComponent) {
        this.graphicsComponent = graphicsComponent;
    }

    public void setOrientation(Double orientation){
        this.orientation = orientation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public PhysicsComponent getPhysicsComponent(){
        return physicsComponent;
    }

    public Double getOrientation(){
        return orientation;
    }

    public GraphicsComponent getGraphicsComponent() {
        return graphicsComponent;
    }

    public ControllerComponent getControllerComponent() {
        return controllerComponent;
    }
}

