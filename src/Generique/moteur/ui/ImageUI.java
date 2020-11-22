package Generique.moteur.ui;

import Generique.moteur.physics.Position;
import javafx.scene.image.Image;

public class ImageUI extends CanvasUI {


    public ImageUI(double sizeX, double sizeY, Position position) {
        super(sizeX, sizeY, position);
    }

    public void drawImage(Image image){
        clear();
        graphicsContext.drawImage(image,position.getX(),position.getY());
    }
}
