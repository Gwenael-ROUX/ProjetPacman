package moteur.controller;

/**
 * Interface des appel systeme pour le controlleur, a chaque inputs clavier
 */
public interface EventController {
    public void handle(KeyboardCode keyCode);
}
