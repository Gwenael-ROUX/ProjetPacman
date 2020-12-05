package moteur.ui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import moteur.core_kernel.Map;
import moteur.physics.Position;

import java.io.InputStream;

public class MenuView extends StackPane {
    InputStream is = getClass().getResourceAsStream("/Font/ARCADE_N.TTF");

    private Button gameButton1P;
    private Button gameButton2P;
    private Button controls;
    private Button helpButton;
    private Button quitButton;

    ImageUI imageUI;
    Image image;

    public MenuView() {
        gameButton1P = new Button();
        gameButton2P = new Button();
        controls = new Button();
        helpButton = new Button();
        quitButton = new Button();
        imageUI= new ImageUI(600,600,new Position(0,0)) ;
        image = new Image("/Image/Menu/pacman_Menu.jpg");
        init();
    }

    public void init() {
        Font font = Font.loadFont(is,13);

        imageUI.drawImage(image);
        getChildren().add(imageUI.getCanvas());

        setButton(gameButton1P,"Start Game -1P",100,240, Color.WHITE,font);
        setButton(gameButton2P,"Start Game -2P",100,265,Color.WHITE,font);
        setButton(controls,"Controle",135,290,Color.WHITE,font);
        setButton(helpButton,"Aide",155,315,Color.WHITE,font);
        setButton(quitButton,"Quitter",140,340,Color.WHITE,font);


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

    public Button getGameButton1P() {
        return gameButton1P;
    }

    public Button getGameButton2P() {
        return gameButton2P;
    }

    public Button getControls() {
        return controls;
    }

    public Button getHelpButton() {
        return helpButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }
}
