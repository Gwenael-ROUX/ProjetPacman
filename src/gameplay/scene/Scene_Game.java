//package gameplay.scene;
//
//import moteur.ui.CanvasUI;
//import moteur.ui.ImageUI;
//import moteur.ui.SceneManager;
//import moteur.ui.TextUI;
//import PasGenerique.gameplay.EntityCharacter;
//import PasGenerique.gameplay.ImageEntity;
//import PasGenerique.gameplay.LevelGenerator;
//import PasGenerique.gameplay.builder.PacmanBuilder;
//import PasGenerique.gameplay.builder.Director;
//import javafx.scene.Group;
//import PasGenerique.gameplay.PacmanGame;
//import PasGenerique.moteurs.Entity;
//import PasGenerique.moteurs.Position;
//import PasGenerique.moteurs.controllers.KeyboardController;
//import PasGenerique.moteurs.controllers.ai.MapRepresentation;
//import PasGenerique.moteurs.physics.BoxCollider;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//
//import java.util.ArrayList;
//import java.util.Timer;
//
//public class Scene_Game extends Group {
//    private Timer timer;
//
//    public Scene_Game(){
//        Director director = new Director();
//        director.setEntityCharacterBuilder(new PacmanBuilder(), new Position(10, 10),
//                new BoxCollider(new Position(10, 10), new Position(10, 10)));
//        director.constructEntityCharacter();
//        EntityCharacter pacman = director.getEntityCharacter();
//
//        ArrayList<Entity> behaviours = new ArrayList<>();
//
//        LevelGenerator lvl = new LevelGenerator(600,600,"/Level/level2.txt");
//        MapRepresentation mapRepresentation = lvl.getMapRepresentation();
//        Entity[][] entities = mapRepresentation.getMatrix();
//        Group group = new Group();
//        int ind = 0;
//        for (Entity[] ent:entities){
//            for (Entity e:ent){
//                if (e != null){
//                    ImageEntity test = (ImageEntity) e;
//                    group.getChildren().add(test.getImageView());
//                    if (!e.getName().equals("mur") && !e.getName().equals("objet") && !e.getName().equals("gomme")){
//                        EntityCharacter test1 = (EntityCharacter) test;
//                        if (e.getName().equals("pacman"))
//                            pacman = test1;
//                    }
//                    behaviours.add(test);
//                }
//            }
//        }
//        PacmanGame game = new PacmanGame(mapRepresentation, new Position(600, 600), behaviours);
//
//        timer = new Timer();
//        timer.scheduleAtFixedRate(game, 0, 500); // Vitesse de rafraichissement
//        KeyboardController controller = (KeyboardController) pacman.getController();
//        setOnKeyPressed(controller.getEventHandler());
//
//        Image coeur = new Image("/Image/Coeur.png",25,25,false,false);
//        Image cerise = new Image("/Image/Cerise.png",25,25,false,false);
//
//
//        ImageUI vie1 =new ImageUI(600,600,new moteur.physics.Position(30,0));
//        ImageUI vie2 =new ImageUI(600,600,new moteur.physics.Position(60,0));
//        ImageUI vie3 =new ImageUI(600,600,new moteur.physics.Position(90,0));
//
//        vie1.drawImage(coeur);
//        vie2.drawImage(coeur);
//        vie3.drawImage(coeur);
//
//        TextUI textUI = new TextUI(600,600,new moteur.physics.Position(450,20));
//        textUI.drawText("Score : 0",Color.WHITE,15);
//        textUI.update("Score : 1000");
//
//        TextUI textUI1 = new TextUI(600,600,new moteur.physics.Position(250,20));
//        textUI1.drawText("Level : 1",Color.WHITE,20);
//        StackPane stackPane = new StackPane();
//        stackPane.getChildren().addAll(group,textUI.getCanvas(),textUI1.getCanvas(),vie1.getCanvas(),vie2.getCanvas(),vie3.getCanvas());
//        getChildren().add(stackPane);
//
//    }
//}
//
//
