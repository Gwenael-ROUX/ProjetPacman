package Generique.moteur.ui;

import Generique.moteur.core_kernel.GameLoop;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SceneGame extends Pane {
    private GraphicsContext gc;
    public SceneGame() {
        setStyle("-fx-background-color: #000000;");
        Canvas canvas = new Canvas( 600, 600 );
        getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        //gc.drawImage(pacman, 0, 0, 30,30);
//        gc.clearRect(0, 0, 30,30);
//        gc.drawImage(pacman, 0, 0, 30,30);
    }

    public GraphicsContext getGc() {
        return gc;
    }
}
