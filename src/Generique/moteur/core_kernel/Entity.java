package Generique.moteur.core_kernel;

import Generique.moteur.physics.PhysicsComponent;
import Generique.moteur.physics.Position;
import Generique.moteur.sound.SoundComponent;
import Generique.moteur.controller.ControllerComponent;
import Generique.moteur.graphique.GraphicsComponent;

public class Entity {
    private Position position;
    private String name;
    private double orientation;
    private ControllerComponent controllerComponent;
    private PhysicsComponent physicsComponent;
    private GraphicsComponent graphicsComponent;
    private SoundComponent soundComponent;

    public Entity(Position position, String name) {
        this.position = position;
        this.name = name;
        this.orientation = 0.0;
    }

    public void move(){
        if(controllerComponent != null)
            controllerComponent.update(this);
        if (physicsComponent != null)
            physicsComponent.update(this);
    }

    public void update(){
        if (graphicsComponent != null)
            graphicsComponent.update(this);
        if (soundComponent != null)
            soundComponent.update(this);
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

    public void setSoundComponent(SoundComponent soundComponent) {
        this.soundComponent = soundComponent;
    }

    public void setOrientation(double orientation){
        this.orientation = orientation;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public PhysicsComponent getPhysicsComponent(){
        return physicsComponent;
    }

    public double getOrientation(){
        return orientation;
    }
}

