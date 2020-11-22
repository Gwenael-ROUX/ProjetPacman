//package gameplay.scene;
//
//import moteur.ui.SceneManager;
//import javafx.application.Application;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class Scene_Main  extends Application {
//
//
//    // Si ça marche pas --module-path C:\Users\eddy-\Desktop\javafx-sdk-11.0.2\lib --add-modules=javafx.controls,javafx.fxml
//    @Override
//    public void start(Stage stage) throws Exception {
//
//        Parent root = new Scene_Menu();
//        stage.setTitle("Pacman");
//        stage.setResizable(false);
//        stage.setScene((new Scene(root,600,600)));
//        SceneManager.setStage(stage);
//        stage.show();
//
//
//    }
//
//    public static void main(String[] args){
//        launch(args);
//    }
//}