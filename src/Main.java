import gameplay.Ghost;
import gameplay.Pacman;
import gameplay.Character;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import moteurs.CharacterBehaviour;
import moteurs.PacmanGame;
import moteurs.Position;
import moteurs.controllers.KeyboardController;
import moteurs.controllers.ai.AI;
import moteurs.controllers.ai.RandomAI;
import moteurs.physics.BoxCollider;
import moteurs.physics.Physics;

import java.io.FileInputStream;
import java.util.Timer;

public class Main extends Application {
    private Timer timer;

    @Override
    public void start(Stage stage) throws Exception {
        Character pacman = new Pacman(20, 20, new BoxCollider(10, 30, 10, 30));
        ImageView pacmanImageView = new ImageView(new Image(new FileInputStream("Image/Pacman.png")));
        pacmanImageView.setFitHeight(20);
        pacmanImageView.setFitWidth(20);
        pacmanImageView.setX(10);
        pacmanImageView.setY(10);
        KeyboardController pacmanController = new KeyboardController();
        Physics pacmanPhysics = new Physics(10, 1, false);

        Character ghost = new Ghost(120, 120, new BoxCollider(110, 130, 10, 30));
        ImageView ghostImageView = new ImageView(new Image(new FileInputStream("Image/Ghost.png")));
        ghostImageView.setFitHeight(20);
        ghostImageView.setFitWidth(20);
        ghostImageView.setX(110);
        ghostImageView.setY(110);
        AI ghostController = new RandomAI();
        Physics ghostPhysics = new Physics(5, 1, false);

        CharacterBehaviour[] behaviours = new CharacterBehaviour[2];
        behaviours[0] = new CharacterBehaviour(pacman, pacmanImageView, pacmanController, pacmanPhysics);
        behaviours[1] = new CharacterBehaviour(ghost, ghostImageView, ghostController, ghostPhysics);

        PacmanGame game = new PacmanGame(new Position(600, 300), behaviours);

        timer = new Timer();
        timer.scheduleAtFixedRate(game, 0, 100); // Vitesse de rafraichissement

        Group root = new Group();
        root.getChildren().add(pacmanImageView);
        root.getChildren().add(ghostImageView);
        Scene scene = new Scene(root,600,300);
        scene.setOnKeyPressed(pacmanController.getEventHandler());
        scene.setFill(Color.BLACK);

        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop(){
        timer.cancel();
    }
}
