package moteur.ui;

import gameplay.scene.MenuView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public abstract class ViewFX extends Pane {
    public abstract void init();

    /**
     * ajoute les élément visuel dans la scene
     * @param element objet à afficher
     */
    public void addToScene(Node element) {
        getChildren().add(element);
    }

    /**
     * change le fond d'écran de  la scène
     * @param path chemin de l'image pour le fond d'écran
     */
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

    public double getHeightScene() {
        return getPrefHeight();
    }

    public double getWidthScene() {
        return getPrefWidth();
    }

    public void setKeyPressed(EventHandler<? super KeyEvent> eventHandler) {
        setOnKeyPressed(eventHandler);
    }
}
