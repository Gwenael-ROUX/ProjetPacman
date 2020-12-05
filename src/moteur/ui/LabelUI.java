package moteur.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;

public class LabelUI {
    Label label;

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

    public void changeFont(InputStream is, int size) {
        Font font = Font.loadFont(is,size);
        label.setFont(font);
    }

    public void changeSize(int size){

    }

    public void setColor(Color color) {
        label.setTextFill(color);
    }

    public void update(String text){
        label.setText(text);
    }
}
