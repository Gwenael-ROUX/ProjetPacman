package moteur.core_kernel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager des evenement programmable
 */
public class EventManager {
    private static EventManager eventManager;
    private List<Event> events;

    private EventManager(){
        events = new ArrayList<>();
    }

    public static EventManager getEventManager(){
        if(eventManager == null){
            eventManager = new EventManager();
        }
        return eventManager;
    }

    /**
     * ajout d'un evenement programable dans la list du manager
     * @param e evenement ajouter
     */
    public void addEvent(Event e){
        events.add(e);
    }

    /**
     * appel des evenement
     * destruction des events périmés
     */
    public void manage() {
        List<Event> toRemove = new ArrayList<>();

        for(Event event : events){
            if(event.getTime() > 0)
                event.update();
            else
                toRemove.add(event);
        }

        for(Event event : toRemove){
            event.handle();
            events.remove(event);
        }
    }
}
