package Generique.Moteur.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public abstract class KeyboardController implements ControllerComponent {

    private EventHandler<? super KeyEvent> eventHandler;

    public KeyboardController(){}

    protected void createHandler(EventController eventController){
        eventHandler = (EventHandler<KeyEvent>) keyEvent -> {
            eventController.handle(KeyboardCode.valueOf(String.valueOf(keyEvent.getCode())));
        };
    }

    public EventHandler<? super KeyEvent> getEventHandler() {
        return eventHandler;
    }
}


