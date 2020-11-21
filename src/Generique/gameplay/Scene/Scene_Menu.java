package Generique.gameplay.Scene;

import Generique.moteur.ui.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Scene_Menu extends GridPane {

    private ImageView imageView;

    private Scene game = new Scene(new Scene_Game(),600,600);

    private static Scene aide = new Scene(new Scene_Aide(),600,600);

    public Scene_Menu() throws FileNotFoundException {

        setVgap(10);
        setHgap(10);

        //Image image = new Image(new FileInputStream("/Image/pacman/pacmanRight.png"));
        //imageView = new ImageView(image);
        /*imageView.setFitHeight(200);
        imageView.setFitWidth(200)*/;
        Button gameButton = new Button("Jouer");
        Button helpButton = new Button("aide");
        Button quitButton = new Button("Quitter");
        //add(imageView,15,0);
        add(gameButton,12,5);
        add(helpButton,12,7);
        add(quitButton,12,10);

        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.setFill(Color.BLACK);
                SceneManager.switchScene(game);
            }
        });

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneManager.switchScene(aide);
            }
        });
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
    }
}
