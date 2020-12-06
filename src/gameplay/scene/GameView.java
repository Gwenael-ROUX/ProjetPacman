package gameplay.scene;
import moteur.ui.ViewFX;

/**
 * Vue correspondant au jeu
 */
public class GameView extends ViewFX {

    public GameView() {
        init();
    }

    @Override
    public void init() {
        setStyle("-fx-background-color: #000000;");
    }
}
