package moteur.controller;

import moteur.core_kernel.Entity;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Controlleur du clavier pour prise en compte par le moteur
 */
public abstract class KeyboardController implements ControllerComponent {

    private EventHandler<? super KeyEvent> eventHandler;

    public KeyboardController(){}

    /**
     * creation de detecteur d'input clavier
     */
    protected void createHandler(EventController eventController){
        eventHandler = (EventHandler<KeyEvent>) keyEvent -> {
            try{
                eventController.handle(KeyboardCode.valueOf(String.valueOf(keyEvent.getCode())));
            } catch (Exception e){
                System.err.println("Warning: " + e.getMessage());
            }
        };
    }

    public EventHandler<? super KeyEvent> getEventHandler() {
        return eventHandler;
    }

    @Override
    public abstract void update(Entity entity);
}


