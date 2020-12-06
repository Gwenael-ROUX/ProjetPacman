package moteur.controller;

/**
 * Interface des appels systeme pour le controlleur, a chaque inputs clavier
 */
public interface EventController {
    public void handle(KeyboardCode keyCode);
}
