package moteur.ui;

import moteur.physics.Position;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextUI extends CanvasUI {

    private  Color color;

    public TextUI(double sizeX, double sizeY, Position position) {
        super(sizeX, sizeY, position);
    }

    public void setHeight(double height){
        graphicsContext.setFont(new Font("Helvetica", height));
    }

    public void drawText(String text, Color color, double taille){
        setHeight(taille);
        graphicsContext.setFill(color);
        this.color =color;
        graphicsContext.fillText(text, position.getX(),position.getY());
    }

    public void update(String text){
        clear();
        graphicsContext.fillText(text,position.getX(), position.getY());

    }


}
