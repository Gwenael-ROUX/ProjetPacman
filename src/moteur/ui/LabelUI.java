package moteur.ui;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.InputStream;

/**
 * Classe permettant de crée graphiquement des textes
 */
public class LabelUI {
    Label label;

    /**
     * Initialise l'affichage du texte
     * @param text texte à afficher
     * @param x position du texte sur l'axe x
     * @param y position du texte sur l'axe y
     */
    public LabelUI(String text, double x, double y) {
        label = new Label();
        label.setText(text);
        label.setLayoutY(y);
        label.setLayoutX(x);
        label.setStyle("-fx-background-color: transparent");
    }

    public Label getLabel() {
        return label;
    }

    /**
     * Change la taille + le font du texte
     * @param is  Font du texte
     * @param size taille du texte
     */
    public void changeFont(InputStream is, int size) {
        Font font = Font.loadFont(is,size);
        label.setFont(font);
    }

    /**
     * Change la couleur du texte
      * @param color couleur
     */
    public void setColor(Color color) {
        label.setTextFill(color.fxcolor);
    }

    /**
     * Méthode pour update le texte
     * @param text texte à update
     */
    public void update(String text){
        label.setText(text);
    }
}
