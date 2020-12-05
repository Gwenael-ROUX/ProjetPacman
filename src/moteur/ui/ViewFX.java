package moteur.ui;

import gameplay.scene.MenuView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public abstract class ViewFX extends Pane {

    public void addToScene(Node element) {
        getChildren().add(element);
    }

    public void setBackgroundScene(String path) {
        Image image2 = new Image(MenuView.class.getResourceAsStream(path));
        BackgroundSize backgroundSize = new BackgroundSize(getPrefHeight(),getPrefWidth(),true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        setBackground(background);
    }

    public void setHeightScene(double height) {
        setPrefHeight(height);
    }

    public void setWidthScene(double width) {
        setPrefWidth(width);
    }

    public void setKeyPressed(EventHandler<? super KeyEvent> eventHandler) {
        setOnKeyPressed(eventHandler);
    }
}
