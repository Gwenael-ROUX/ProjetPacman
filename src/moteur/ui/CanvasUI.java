package moteur.ui;


import moteur.physics.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public  abstract class CanvasUI {

    private Canvas canvas;
    protected GraphicsContext graphicsContext;
    protected double tailleX;
    protected double tailleY;

    protected Position position;

    public CanvasUI( double sizeX, double sizeY, Position position){
        canvas = new Canvas(sizeX,sizeY);
        graphicsContext = canvas.getGraphicsContext2D();
        tailleX = sizeX;
        tailleY =sizeY;
        this.position = position;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public void clear(){
        graphicsContext.clearRect(0,0,tailleX,tailleY);
    }

    public void setVisible(boolean visible){
        canvas.setVisible(visible);
    }

}
