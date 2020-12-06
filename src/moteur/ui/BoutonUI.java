package moteur.ui;

import javafx.scene.control.Button;
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

    public void setAction(SceneHandler eventEventHandler) {
        button.setOnAction(actionEvent -> eventEventHandler.handle());
    }

    public void changeFont(InputStream is, int size) {
        Font font = Font.loadFont(is,size);
        button.setFont(font);
    }

    public void setColor(Color color) {
        button.setTextFill(color.fxcolor);
    }
}
