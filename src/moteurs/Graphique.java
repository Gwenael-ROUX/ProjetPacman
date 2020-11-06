package moteurs;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Graphique extends Application {
    private Group root;
    private Scene scene;
    private ImageView imageView;

    @Override
    public void start(Stage stage) throws Exception {
        root = new Group();

        Image image = new Image(new FileInputStream("Image/Pacman.png"));
        imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        root.getChildren().add(imageView);

        scene = new Scene(root,600,300);
        scene.setFill(Color.BLACK);
        Controller controller = new Controller(this);
        scene.setOnKeyPressed(controller.getEventHandler());
        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }

    public void moveVertical(double position) {
        imageView.setY(imageView.getY() + position);
    }

    public void moveHorizontal(double position) {
        imageView.setX(imageView.getX() + position);
    }
}
