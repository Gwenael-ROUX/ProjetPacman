package Generique.moteur.core_kernel;


import Generique.moteur.ui.SceneGame;
import Generique.moteur.ui.SceneMain;
import Generique.moteur.ui.SceneManager;
import Generique.moteur.ui.SceneManager2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameLoop extends Application {
    GameManager gameManager;
    SceneManager sceneManager;

    @Override
    public void start(Stage stage) {
//        stage.setTitle("Pacman");
//
//        Group root = new Group();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//
//        //TODO initialiser scene et canvas avec scene manager
//
//        Canvas canvas = new Canvas( 512, 512 );
//        root.getChildren().add(canvas);
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        scene.setFill(Color.BLACK);

        SceneManager2 sceneManager2 = new SceneManager2(stage, "pacman");
        SceneMain sceneMain =  new SceneMain();

        final long startNanoTime = System.nanoTime();
        Timer timer = Timer.getInstance();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                timer.setTime(t);

                sceneManager2.stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.D)
                            sceneManager2.setScene(new SceneGame());
                    }
                });

                //gameManager.update();

//                gc.drawImage(pacman, 0, 0, 30,30);
//                gc.drawImage(pacman, 470,470, 30,30);
//                if (t > 3) {
//                    System.out.println("c bon");
//                    root.getChildren().remove(canvas);
//                    Canvas canvas2 = new Canvas( 400, 400 );
//                    root.getChildren().add(canvas2);
//                }
            }
        }.start();

        sceneManager2.show(sceneMain);
    }
}
