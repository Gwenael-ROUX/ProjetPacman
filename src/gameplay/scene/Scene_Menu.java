package gameplay.scene;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import moteur.core_kernel.GameLoop;
import moteur.physics.Position;
import moteur.ui.ImageUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import moteur.ui.SceneGame;
import moteur.ui.SceneManager2;

import java.io.InputStream;

public class Scene_Menu extends StackPane {

    InputStream is =getClass().getResourceAsStream("/Font/ARCADE_N.TTF");

//    private Scene game = new Scene(new Scene_Game(),600,600);
    Button gameButton1P = new Button();
    Button gameButton2P = new Button();
    Button controls = new Button();
    Button helpButton = new Button();
    Button quitButton = new Button();

    ImageUI imageUI= new ImageUI(600,600,new Position(0,0)) ;
    Image image = new Image("/Image/Menu/pacman_Menu.jpg");

    public Scene_Menu(SceneManager2 sceneManager2){

        Font font = Font.loadFont(is,13);

        imageUI.drawImage(image);
        getChildren().add(imageUI.getCanvas());

        setButton(gameButton1P,"Start Game -1P",100,240,Color.WHITE,font);
        setButton(gameButton2P,"Start Game -2P",100,265,Color.WHITE,font);
        setButton(controls,"Controle",135,290,Color.WHITE,font);
        setButton(helpButton,"Aide",155,315,Color.WHITE,font);
        setButton(quitButton,"Quitter",140,340,Color.WHITE,font);

        gameButton1P.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    GameLoop gameLoop = new GameLoop();
                    gameLoop.start(sceneManager2.getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        gameButton2P.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    GameLoop gameLoop = new GameLoop();
                    gameLoop.start(sceneManager2.getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        controls.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent root = new Scene_Aide(sceneManager2);
                sceneManager2.getStage().setScene(new Scene(root,400,400));
            }
        });
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        Pane root = new Pane();
        root.getChildren().addAll(gameButton1P,gameButton2P,controls,helpButton,quitButton);
        getChildren().add(root);
    }

    private Button setButton(Button button,String text,double x, double y, Color color,Font font){
        button.setText(text);
        button.setTextFill(color);
        button.setFont(font);
        button.setLayoutY(y);
        button.setLayoutX(x);
        button.setStyle("-fx-background-color: transparent");
        return button;
    }
}
