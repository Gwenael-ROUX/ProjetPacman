import PasGenerique.gameplay.EntityCharacter;
import PasGenerique.gameplay.LevelGenerator;
import PasGenerique.moteurs.Entity;
import PasGenerique.moteurs.Position;
import PasGenerique.moteurs.controllers.ai.BasicPathFinder;
import PasGenerique.moteurs.controllers.ai.MapRepresentation;
import PasGenerique.moteurs.controllers.ai.ShortestPathAI;
import PasGenerique.moteurs.physics.Displacement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUnitIA {

    @Test
    public void ShortestPathIALigneDroite() {
        LevelGenerator levelGenerator = new LevelGenerator(600,600, "/Level/Test/lignedroite.txt");
        MapRepresentation mapRepresentation = levelGenerator.getMapRepresentation();
        Entity[][] entities = mapRepresentation.getMatrix();
        ShortestPathAI shortestPathAI = new ShortestPathAI(new BasicPathFinder(mapRepresentation));

        EntityCharacter pacman = null;
        EntityCharacter ghost = null;
        for (Entity[] ent :entities){
            for (Entity e:ent){
                if (e != null) {
                    if (e.getName().equals("pacman"))
                        pacman = (EntityCharacter) e;
                    else if (e.getName().equals("ghost"))
                        ghost = (EntityCharacter) e;
                }
            }
        }
        shortestPathAI.setOrigin(ghost);
        shortestPathAI.setTarget(pacman);

        assertEquals(Displacement.LEFT, shortestPathAI.move());

        assert ghost != null;
        Position currentPos = mapRepresentation.getPositionEntity(ghost);
        mapRepresentation.swap((int)currentPos.getX(), (int)currentPos.getY(), (int)currentPos.getX()-1, (int)currentPos.getY());
        assertEquals(Displacement.LEFT, shortestPathAI.move());
    }

    @Test
    public void ShortestPathIAMur() {
        LevelGenerator levelGenerator = new LevelGenerator(600,600, "/Level/Test/mur.txt");
        MapRepresentation mapRepresentation = levelGenerator.getMapRepresentation();
        Entity[][] entities = mapRepresentation.getMatrix();
        ShortestPathAI shortestPathAI = new ShortestPathAI(new BasicPathFinder(mapRepresentation));

        EntityCharacter pacman = null;
        EntityCharacter ghost = null;
        for (Entity[] ent :entities){
            for (Entity e:ent){
                if (e != null) {
                    if (e.getName().equals("pacman"))
                        pacman = (EntityCharacter) e;
                    else if (e.getName().equals("ghost"))
                        ghost = (EntityCharacter) e;
                }
            }
        }
        shortestPathAI.setOrigin(ghost);
        shortestPathAI.setTarget(pacman);

        assertEquals(Displacement.LEFT, shortestPathAI.move());

        assert ghost != null;

        Position currentPos = mapRepresentation.getPositionEntity(ghost);
        mapRepresentation.swap((int)currentPos.getX(), (int)currentPos.getY(), (int)currentPos.getX()-1, (int)currentPos.getY());
        assertEquals(Displacement.DOWN, shortestPathAI.move());

        Position currentPos2 = mapRepresentation.getPositionEntity(ghost);
        mapRepresentation.swap((int)currentPos2.getX(), (int)currentPos2.getY(), (int)currentPos2.getX(), (int)currentPos2.getY()+1);
        assertEquals(Displacement.LEFT, shortestPathAI.move());

        Position currentPos3 = mapRepresentation.getPositionEntity(ghost);
        mapRepresentation.swap((int)currentPos3.getX(), (int)currentPos3.getY(), (int)currentPos3.getX()-1, (int)currentPos3.getY());
        assertEquals(Displacement.LEFT, shortestPathAI.move());

        Position currentPos4 = mapRepresentation.getPositionEntity(ghost);
        mapRepresentation.swap((int)currentPos4.getX(), (int)currentPos4.getY(), (int)currentPos4.getX()-1, (int)currentPos4.getY());
        assertTrue(shortestPathAI.move() == Displacement.LEFT || shortestPathAI.move() == Displacement.UP);
    }

    @Test
    public void ShortestPathIAPacManDisplacement() {
        LevelGenerator levelGenerator = new LevelGenerator(600,600, "/Level/Test/pacmanDisplacement.txt");
        MapRepresentation mapRepresentation = levelGenerator.getMapRepresentation();
        Entity[][] entities = mapRepresentation.getMatrix();
        ShortestPathAI shortestPathAI = new ShortestPathAI(new BasicPathFinder(mapRepresentation));

        EntityCharacter pacman = null;
        EntityCharacter ghost = null;
        for (Entity[] ent :entities){
            for (Entity e:ent){
                if (e != null) {
                    if (e.getName().equals("pacman"))
                        pacman = (EntityCharacter) e;
                    else if (e.getName().equals("ghost"))
                        ghost = (EntityCharacter) e;
                }
            }
        }
        shortestPathAI.setOrigin(ghost);
        shortestPathAI.setTarget(pacman);

        assertEquals(Displacement.LEFT, shortestPathAI.move());

        assert ghost != null;

        Position currentPos = mapRepresentation.getPositionEntity(ghost);
        mapRepresentation.swap((int)currentPos.getX(), (int)currentPos.getY(), (int)currentPos.getX()-1, (int)currentPos.getY());
        Position currentPosPacman = mapRepresentation.getPositionEntity(pacman);
        mapRepresentation.swap((int)currentPosPacman.getX(), (int)currentPosPacman.getY(), (int)currentPosPacman.getX(), (int)currentPosPacman.getY()+1);
        assertEquals(Displacement.LEFT, shortestPathAI.move());

        Position currentPos2 = mapRepresentation.getPositionEntity(ghost);
        mapRepresentation.swap((int)currentPos2.getX(), (int)currentPos2.getY(), (int)currentPos2.getX()-1, (int)currentPos2.getY());
        Position currentPosPacman2 = mapRepresentation.getPositionEntity(pacman);
        mapRepresentation.swap((int)currentPosPacman2.getX(), (int)currentPosPacman2.getY(), (int)currentPosPacman2.getX(), (int)currentPosPacman2.getY()+1);
        assertEquals(Displacement.DOWN, shortestPathAI.move());
    }
}
