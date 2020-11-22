//package gameplay.scene;
//
//import moteur.ui.CanvasUI;
//import moteur.ui.SceneManager;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
//
//import java.io.FileNotFoundException;
//
//public class Scene_Menu extends GridPane {
//
//    private ImageView imageView;
//
//    private Scene game = new Scene(new Scene_Game(),600,600);
//
//    private static Scene aide = new Scene(new Scene_Aide(),600,600);
//
//    public Scene_Menu() throws FileNotFoundException {
//
//        setVgap(10);
//        setHgap(10);
//
//        //Image image = new Image(new FileInputStream("/Image/pacman/pacmanRight.png"));
//        //imageView = new ImageView(image);
//        /*imageView.setFitHeight(200);
//        imageView.setFitWidth(200)*/;
//        Button gameButton = new Button("Jouer");
//        Button helpButton = new Button("aide");
//        Button quitButton = new Button("Quitter");
//        //add(imageView,15,0);
//        Text score = new Text("text");
//        score.setX(100);
//        score.setY(100);
//        add(gameButton,12,5);
//        add(helpButton,12,7);
//        add(quitButton,12,10);
//        score.setVisible(true);
//
//        gameButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                game.setFill(Color.BLACK);
//                SceneManager.switchScene(game);
//            }
//        });
//
//        helpButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                SceneManager.switchScene(aide);
//            }
//        });
//        quitButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                Platform.exit();
//            }
//        });
//    }
//}
