import gameplay.EntityCharacter;import gameplay.ImageEntity;import gameplay.LevelGenerator;import gameplay.builder.PacmanBuilder;import gameplay.builder.Director;import javafx.application.Application;        import javafx.scene.Group;        import javafx.scene.Scene;import javafx.scene.paint.Color;        import javafx.stage.Stage;        import gameplay.PacmanGame;        import moteurs.Entity;        import moteurs.Position;        import moteurs.controllers.KeyboardController;        import moteurs.physics.BoxCollider;import java.util.ArrayList;import java.util.Timer;public class Main extends Application {    private Timer timer;    public static void main(String[] args) {        launch(args);    }    @Override    public void start(Stage stage) {        Director director = new Director();        director.setEntityCharacterBuilder(new PacmanBuilder(), new Position(10, 10),                new BoxCollider(new Position(10, 10), new Position(10, 10)));        director.constructEntityCharacter();        EntityCharacter pacman = director.getEntityCharacter();        ArrayList<Entity> behaviours = new ArrayList<>();        LevelGenerator lvl = new LevelGenerator(600,600,"/Level/level2.txt");        Entity[][] entities = lvl.getMatrix();        Group root = new Group();        int ind = 0;        for (Entity[] ent:entities){            for (Entity e:ent){                if (e != null){                    ImageEntity test = (ImageEntity) e;                    root.getChildren().add(test.getImageView());                    if (!e.getName().equals("mur")){                        EntityCharacter test1 = (EntityCharacter) test;                        if (e.getName().equals("pacman"))                            pacman = test1;                    }                    behaviours.add(test);                }            }        }        PacmanGame game = new PacmanGame(new Position(600, 600), behaviours);        timer = new Timer();        timer.scheduleAtFixedRate(game, 0, 100); // Vitesse de rafraichissement        Scene scene = new Scene(root,600,600);        KeyboardController controller = (KeyboardController) pacman.getController();        scene.setOnKeyPressed(controller.getEventHandler());        scene.setFill(Color.BLACK);        stage.setTitle("PacMan");        stage.setScene(scene);        stage.show();    }    @Override    public void stop(){        timer.cancel();    }}