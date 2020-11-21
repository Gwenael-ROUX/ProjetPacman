package Generique.gameplay.Scene;

import PasGenerique.gameplay.EntityCharacter;
import PasGenerique.gameplay.ImageEntity;
import PasGenerique.gameplay.LevelGenerator;
import PasGenerique.gameplay.builder.PacmanBuilder;
import PasGenerique.gameplay.builder.Director;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import PasGenerique.gameplay.PacmanGame;
import PasGenerique.moteurs.Entity;
import PasGenerique.moteurs.Position;
import PasGenerique.moteurs.controllers.KeyboardController;
import PasGenerique.moteurs.controllers.ai.MapRepresentation;
import PasGenerique.moteurs.physics.BoxCollider;
import java.util.ArrayList;
import java.util.Timer;


public class Scene_Game extends Group {
    private Timer timer;

    public Scene_Game(){

        Director director = new Director();
        director.setEntityCharacterBuilder(new PacmanBuilder(), new Position(10, 10),
                new BoxCollider(new Position(10, 10), new Position(10, 10)));
        director.constructEntityCharacter();
        EntityCharacter pacman = director.getEntityCharacter();

        ArrayList<Entity> behaviours = new ArrayList<>();

        LevelGenerator lvl = new LevelGenerator(600,600,"/Level/level2.txt");
        MapRepresentation mapRepresentation = lvl.getMapRepresentation();
        Entity[][] entities = mapRepresentation.getMatrix();

        int ind = 0;
        for (Entity[] ent:entities){
            for (Entity e:ent){
                if (e != null){
                    ImageEntity test = (ImageEntity) e;
                    getChildren().add(test.getImageView());
                    if (!e.getName().equals("mur") && !e.getName().equals("objet") && !e.getName().equals("gomme")){
                        EntityCharacter test1 = (EntityCharacter) test;
                        if (e.getName().equals("pacman"))
                            pacman = test1;
                    }
                    behaviours.add(test);
                }
            }
        }
        PacmanGame game = new PacmanGame(mapRepresentation, new Position(600, 600), behaviours);

        timer = new Timer();
        timer.scheduleAtFixedRate(game, 0, 500); // Vitesse de rafraichissement
        KeyboardController controller = (KeyboardController) pacman.getController();
        setOnKeyPressed(controller.getEventHandler());
    }
}


