package Generique.moteur.core_kernel;


import Generique.moteur.ui.SceneManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameLoop extends Application {
    GameManager gameManager;
    SceneManager sceneManager;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pacman");

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        //TODO initialiser scene et canvas avec scene manager

        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        scene.setFill(Color.BLACK);

        final long startNanoTime = System.nanoTime();
        Timer timer = Timer.getInstance();

        Image pacman = new Image(GameLoop.class.getResourceAsStream("/Image/pacman/pacmanClose.png"));
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                timer.setTime(t);

                gameManager.update();

                gc.drawImage(pacman, 0, 0);
            }
        }.start();

        stage.show();
    }
}
