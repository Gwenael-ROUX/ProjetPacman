package moteurs.controllers;import javafx.event.EventHandler;import javafx.scene.input.KeyEvent;import moteurs.physics.Displacement;public class KeyboardController implements Controller {    private EventHandler<? super KeyEvent> eventHandler;    private Displacement nextMove;    public KeyboardController(){        nextMove = Displacement.NOTHING;        eventHandler = (EventHandler<KeyEvent>) keyEvent -> {            switch (keyEvent.getCode()) {                case Z :                    nextMove = Displacement.UP;                    break;                case Q :                    nextMove = Displacement.LEFT;                    break;                case S :                    nextMove = Displacement.DOWN;                    break;                case D :                    nextMove = Displacement.RIGHT;                    break;                default:                    nextMove = Displacement.NOTHING;                    break;            }        };    }    public EventHandler<? super KeyEvent> getEventHandler() {        return eventHandler;    }    @Override    public Displacement move() {        Displacement res = nextMove;        nextMove = Displacement.NOTHING;        return res;    }}