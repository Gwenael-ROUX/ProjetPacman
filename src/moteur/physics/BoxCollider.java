package moteur.physics;

public class BoxCollider implements Collider {
    private Position position1;
    private Position position2;
    private Position centerPosition;


    public BoxCollider(Position position1, Position position2) {
        this.position1 = position1;
        this.position2 = position2;
        double centerX = position1.getX() + (position2.getX()-position1.getX())/2;
        double centerY = position1.getY() + (position2.getY()-position1.getY())/2;
        this.centerPosition = new Position(centerX, centerY);
    }

    @Override
    public void update(Position position_) {
        Position position = new Position(position_.getX()+(position2.getX()-position1.getX())/2, position_.getY()+(position2.getY()-position1.getY())/2);
        if (position.getX()>centerPosition.getX()){
            position1.setX(position1.getX()+(position.getX()-centerPosition.getX()));
            position2.setX(position2.getX()+(position.getX()-centerPosition.getX()));
            centerPosition.setX(position.getX());
        }
        else if (position.getX()<centerPosition.getX()){
            position1.setX(position1.getX()-(centerPosition.getX()-position.getX()));
            position2.setX(position2.getX()-(centerPosition.getX()-position.getX()));
            centerPosition.setX(position.getX());
        }
        if (position.getY()>centerPosition.getY()){
            position1.setY(position1.getY()+(position.getY()-centerPosition.getY()));
            position2.setY(position2.getY()+(position.getY()-centerPosition.getY()));
            centerPosition.setY(position.getY());
        }
        else if (position.getY()<centerPosition.getY()){
            position1.setY(position1.getY()-(centerPosition.getY()-position.getY()));
            position2.setY(position2.getY()-(centerPosition.getY()-position.getY()));
            centerPosition.setY(position.getY());
        }
    }

    // TODO : suppress cast in the future
    @Override
    public boolean hit(Collider collider) {
        BoxCollider boxCollider = (BoxCollider) collider;
        return (this.position1.getX() < boxCollider.getPosition2().getX() &&
                this.position2.getX() > boxCollider.getPosition1().getX() &&
                this.position1.getY() < boxCollider.getPosition2().getY() &&
                this.position2.getY() > boxCollider.getPosition1().getY());
    }

    @Override
    public boolean exit(Position p1, Position p2){
        return (this.position1.getX() < p1.getX() ||
                this.position1.getY() < p1.getY() ||
                this.position2.getX() > p2.getX() ||
                this.position2.getY() > p2.getY());
    }

    public Position getPosition1() {
        return position1;
    }

    public Position getPosition2() {
        return position2;
    }
}
