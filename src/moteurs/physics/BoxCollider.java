package moteurs.physics;

import moteurs.Position;

public class BoxCollider implements Collider {
    private double x1, x2, y1, y2, centerX, centerY;

    public BoxCollider(double x1, double x2, double y1, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.centerX = x1 + (x2-x1)/2;
        this.centerY = y1 + (y2-y1)/2;
    }

    @Override
    public void move(Double newPositionX, Double newPositionY) {
        if (newPositionX>this.centerX){
            this.x1 = x1+(newPositionX-this.centerX);
            this.x2 = x2+(newPositionX-this.centerX);
            this.centerX = newPositionX;
        }
        else if (newPositionX<this.centerX){
            this.x1 = x1-(this.centerX-newPositionX);
            this.x2 = x2-(this.centerX-newPositionX);
            this.centerX = newPositionX;
        }
        if (newPositionY>this.centerY){
            this.y1 = y1+(newPositionY-this.centerY);
            this.y2 = y2+(newPositionY-this.centerY);
            this.centerY = newPositionY;
        }
        else if (newPositionY<this.centerY){
            this.y1 = y1-(this.centerY-newPositionY);
            this.y2 = y2-(this.centerY-newPositionY);
            this.centerY = newPositionY;
        }
    }

    @Override
    public boolean hit(BoxCollider collider) {
        return (this.x1 < collider.getX2() &&
                this.x2 > collider.getX1() &&
                this.y1 < collider.getY2() &&
                this.y2 > collider.getY1());
    }

    @Override
    public boolean exit(Position p1, Position p2){
        return (this.x1 < p1.getX() ||
                this.y1 < p1.getY() ||
                this.x2 > p2.getX() ||
                this.y2 > p2.getY());
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }
}
