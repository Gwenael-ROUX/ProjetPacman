package moteur.core_kernel;

/**
 * Classe pour la création d'evenement programmable
 */
public abstract class Event {
    protected Entity entity;
    protected int time;

    protected Event(Entity entity){
        this.entity = entity;
        this.time = 0;
    }

    protected Event(Entity entity, int time){
        this.time = time;
    }

    /**
     * fonction d'appel lors du déclenchement de l'evenement creer
     */
    public abstract void handle();

    public void update(){
        time--;
    }

    public int getTime(){
        return time;
    }
}
