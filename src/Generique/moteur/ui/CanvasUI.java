package Generique.moteur.ui;

import Generique.moteur.physics.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class CanvasUI {
    private Position position;
    private double width;
    private double height;

    private Canvas canvas;
    private GraphicsContext context;

    public CanvasUI(Position position, double width, double height) {
        this.position = position;
        this.height = height;
        this.width = width;

        canvas = new Canvas();
        context = canvas.getGraphicsContext2D();

        canvas.setHeight(this.height);
        canvas.setWidth(this.width);
        context.moveTo(position.getX(), position.getY());

        canvas.heightProperty().bind(canvas.heightProperty());
        canvas.widthProperty().bind(canvas.widthProperty());
    }

    public void refreshProperties(){
        canvas.setHeight(this.height);
        canvas.setWidth(this.width);
        context.moveTo(position.getX(), position.getY());

        canvas.heightProperty().bind(canvas.heightProperty());
        canvas.widthProperty().bind(canvas.widthProperty());
    }


    public void setPosition(Position position){
        context.moveTo(position.getX(), position.getY());
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public GraphicsContext getGraphicsContext() {
        return this.canvas.getGraphicsContext2D();
    }

    public void clearCanvas(){
        context.clearRect(0,0, this.width, this.height);
    }

    public void drawText(String text, double posX, double posY){
        context.fillText(text, posX, posY);
    }

    public void drawText(String text, double posX, double posY, double maxWidth){
        context.fillText(text, posX, posY, maxWidth);
    }

    public void drawSimpleImage(Image img, double x, double y){
        context.drawImage(img, x, y);
    }

    public void drawAnimatedImage(Image img, double spriteWidth, double spriteHeight, double posDrawX, double posDrawY, double drawWidth, double drawHeight){
        context.drawImage(img, 0, 0, spriteWidth, spriteHeight, posDrawX, posDrawY, drawWidth, drawHeight);
    }

    public void setVisible(boolean isVisible){
        canvas.setVisible(isVisible);
    }
}
