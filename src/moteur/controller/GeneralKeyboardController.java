package moteur.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import java.util.List;

/**
 * Gere plusieurs Keyboard handler
 */
public class GeneralKeyboardController {
    private List<KeyboardController> controllers;
    private EventHandler<KeyEvent> eventHandler;

    public GeneralKeyboardController(List<KeyboardController> controllers){
        this.controllers = controllers;
        createHandler();
    }

    /**
     * Cree un keyboard handler, et ajout au manager d'event associe
     */
    private void createHandler(){
        eventHandler = event -> {
            for(KeyboardController controller : controllers){
                controller.getEventHandler().handle(event);
            }
        };
    }

    public EventHandler<KeyEvent> getEventHandler(){
        return eventHandler;
    }
}
