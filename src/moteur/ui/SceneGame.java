package moteur.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class SceneGame extends Pane {
    private GraphicsContext gc;
    public SceneGame() {
        setStyle("-fx-background-color: #000000;");
    }

    public GraphicsContext getGc() {
        return gc;
    }
}
