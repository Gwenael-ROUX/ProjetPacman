package moteur.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;

public class BoutonUI {
    private Button button;

    public BoutonUI(String text, double x, double y) {
        button = new Button();
        button.setText(text);
        button.setLayoutY(y);
        button.setLayoutX(x);
        button.setStyle("-fx-background-color: transparent");

    }

    public Button getButton() {
        return button;
    }

    public void setAction(EventHandler<ActionEvent> eventEventHandler) {
        button.setOnAction(eventEventHandler);
    }

    public void changeFont(InputStream is, int size) {
        Font font = Font.loadFont(is,size);
        button.setFont(font);
    }

    public void setColor(Color color) {
        button.setTextFill(color);
    }
}
