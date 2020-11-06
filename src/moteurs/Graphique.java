package moteurs;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Graphique extends Application {
    private Group root;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        root = new Group();

        Image image = new Image(new FileInputStream(""));
        ImageView imageView = new ImageView(image);
        root.getChildren().add(imageView);

        scene = new Scene(root,600,300);
        stage.setTitle("PacMan");
        stage.setScene(scene);
        stage.show();
    }
}
