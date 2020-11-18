package Generique.Moteur;

public class Entity {
    private Position position;
    private String name;
    private ControllerComponent controllerComponent;
    private PhysicsComponent physicsComponent;
    private GraphicsComponent graphicsComponent;
    private SoundComponent soundComponent;

    public Entity(Position position, String name) {
        this.position = position;
        this.name = name;
    }

    public void update(){
        if (controllerComponent != null)
            controllerComponent.update(this);
        if (physicsComponent != null)
            physicsComponent.update(this);
        if (graphicsComponent != null)
            graphicsComponent.update(this);
        if (soundComponent != null)
            soundComponent.update(this);
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

    public void setSoundComponent(SoundComponent soundComponent) {
        this.soundComponent = soundComponent;
    }
}
