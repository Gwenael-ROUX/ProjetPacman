package moteurs;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Controller {
    private EventHandler<? super KeyEvent> eventHandler;
    private Graphique graphique;

    Controller(Graphique graphique) {
        this.graphique = graphique;
        eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case Z :
                        graphique.moveVertical(-20);
                        break;
                    case Q :
                        graphique.moveHorizontal(-20);
                        break;
                    case S :
                        graphique.moveVertical(20);
                        break;
                    case D :
                        graphique.moveHorizontal(20);
                        break;
                }
            }
        };
    }

    public EventHandler<? super KeyEvent> getEventHandler() {
        return eventHandler;
    }
}
