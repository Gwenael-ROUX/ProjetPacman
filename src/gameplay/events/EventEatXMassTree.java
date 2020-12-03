package gameplay.events;

import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;

public class EventEatXMassTree extends Event {
    protected EventEatXMassTree(Entity entity, int time) {
        super(entity, time);
    }

    @Override
    public void handle() {

    }
}
