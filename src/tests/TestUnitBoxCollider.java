package tests;

import moteurs.Position;
import moteurs.physics.BoxCollider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestUnitBoxCollider {

    @Test
    public void hitSimpleTest(){
        BoxCollider collider1 = new BoxCollider(0, 5, 0, 5);
        BoxCollider collider2 = new BoxCollider(2, 7, 2, 7);
        BoxCollider collider3 = new BoxCollider(5, 10, 5, 10);

        assertTrue(collider1.hit(collider2));
        assertFalse(collider1.hit(collider3));
        assertTrue(collider2.hit(collider3));
    }

    @Test
    public void moveSimpleTest(){
        BoxCollider collider1 = new BoxCollider(0, 3, 0, 5); // Center 1.5, 2.5
        collider1.move(3.,5.); // Center now 3, 5

        assertEquals(1.5, collider1.getX1());
        assertEquals(2.5, collider1.getY1());
        assertEquals(4.5, collider1.getX2());
        assertEquals(7.5, collider1.getY2());
    }

    @Test
    public void hitAfterDisplacementTest(){
        BoxCollider collider1 = new BoxCollider(0, 5, 0, 5);
        BoxCollider collider2 = new BoxCollider(2, 7, 2, 7);

        collider2.move(6.5, 6.5);
        assertTrue(collider1.hit(collider2));

        collider2.move(7.5, 7.5);
        assertFalse(collider1.hit(collider2));
    }

    @Test
    public void exitSimpleTest(){
        BoxCollider collider1 = new BoxCollider(595, 605, 0, 10);

        assertTrue(collider1.exit(new Position(0, 0), new Position(600, 300)));
    }

    @Test
    public void exitAfterDisplacement(){
        BoxCollider collider1 = new BoxCollider(0, 5, 0, 5);

        assertFalse(collider1.exit(new Position(0, 0), new Position(600, 300)));

        collider1.move(.0, .0);

        assertTrue(collider1.exit(new Position(0, 0), new Position(600, 300)));

        collider1.move(599., 2.5);

        assertTrue(collider1.exit(new Position(0, 0), new Position(600, 300)));
    }
}