package moteurs;

public class BoxCollider implements Collider{
    Double x1,x2,y1,y2,centerX,centerY;

    public BoxCollider(Double x1, Double x2, Double y1, Double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.centerX = (x2-x1)/2;
        this.centerY = (y2-y1)/2;
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
    public Boolean hit(BoxCollider collider) {
        return (this.x1 < collider.getX2() &&
                this.x2 > collider.getX1() &&
                this.y1 < collider.getY2() &&
                this.y2 > collider.getY1());
    }

    public Double getX1() {
        return x1;
    }

    public Double getX2() {
        return x2;
    }

    public Double getY1() {
        return y1;
    }

    public Double getY2() {
        return y2;
    }
}
