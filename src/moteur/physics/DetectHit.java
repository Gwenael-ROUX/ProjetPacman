package moteur.physics;

class DetectHit {

    private static boolean hit(BoxCollider collider1, BoxCollider collider2){
        return (collider1.getPosition1().getX() < collider2.getPosition2().getX() &&
                collider1.getPosition2().getX() > collider2.getPosition1().getX() &&
                collider1.getPosition1().getY() < collider2.getPosition2().getY() &&
                collider1.getPosition2().getY() > collider2.getPosition1().getY());
    }

    static boolean hit(BoxCollider boxCollider, Collider collider){
        if(collider instanceof BoxCollider)
            return hit(boxCollider, (BoxCollider)collider);
        return false;
    }
}
