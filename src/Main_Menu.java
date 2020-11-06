import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main_Menu extends Application {

    private Scene scene;



    @Override
    public void start(Stage stage) throws Exception {
        Image pacMan = new Image(new FileInputStream("C:\\Users\\eddy-\\Documents\\Master 1 Informatique\\ProjetPacman\\Image"));
        ImageView imageView = new ImageView(pacMan);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        Group root = new Group(imageView);

        this.scene = new Scene(root, 700,700);
        stage.setScene(scene);
        stage.setTitle("PacMan");
        stage.show();
    }

    public static void main(String args[]) {


    }

}
