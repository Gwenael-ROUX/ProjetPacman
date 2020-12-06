package moteur.ui;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import java.io.InputStream;

/**
 * Classe permettant de créer des bouttons
 */
public class BoutonUI {
    private Button button;

    /**
     * configure le bouton
     * @param text le texte aaffiché sur le bouton
     * @param x du bouton surposition sur l'axe x
     * @param y du bouton sur l'axe y
     */
    public  BoutonUI(String text, double x, double y) {
        button = new Button();
        button.setText(text);
        button.setLayoutY(y);
        button.setLayoutX(x);
        button.setStyle("-fx-background-color: transparent");

    }

    public Button getButton() {
        return button;
    }

    public void setAction(SceneHandler eventEventHandler) {
        button.setOnAction(actionEvent -> eventEventHandler.handle());
    }

    /**
     * Change la taille du texte ainssi que le font
     * @param is  Font du texte
     * @param size taille du texte
     */
    public void changeFont(InputStream is, int size) {
        Font font = Font.loadFont(is,size);
        button.setFont(font);
    }

    /**
     * Change la couleur du texte du bouton
     * @param color couleur
     */
    public void setColor(Color color) {
        button.setTextFill(color.fxcolor);
    }
}
