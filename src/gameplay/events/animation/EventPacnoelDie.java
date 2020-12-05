package gameplay.events.animation;

import moteur.core_kernel.Entity;
import moteur.core_kernel.Event;
import moteur.core_kernel.EventManager;

public class EventPacnoelDie extends Event {
    private Entity entity_owned;

    protected EventPacnoelDie(Entity entity_owned, Entity entity, int time) {
        super(entity, time);
        this.entity_owned = entity_owned;
    }

    @Override
    public void handle() {
        entity_owned.getGraphicsComponent().getAnimationManager().setCurrentAnimation("mortpacnoel");
        EventManager.getEventManager().addEvent(new EventAnimMort(entity_owned, entity, 100));
    }
}
