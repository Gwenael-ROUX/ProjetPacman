package moteurs;import moteurs.physics.Collider;public abstract class Entity {    private Position position;    private Collider collider;    private String name;    public Entity(){    }    public void move(Position nextPosition){        collider.move(nextPosition);        position.setX(nextPosition.getX());        position.setY(nextPosition.getY());    }    public Position getPosition() {        return position;    }    public Collider getCollider(){        return collider;    }    public String getName() {        return name;    }    public void setPosition(Position position) {        this.position = position;    }    public void setCollider(Collider collider) {        this.collider = collider;    }    public void setName(String name) {        this.name = name;    }}