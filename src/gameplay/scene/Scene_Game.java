package gameplay.scene;

import gameplay.LevelGenerator;
import gameplay.controller.PacmanKeyboardController;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import moteur.core_kernel.GameManager;
import moteur.ui.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Scene_Game extends StackPane {


    public Scene_Game( SceneManager2 sceneManager2) throws Exception {

        LevelGenerator levelGenerator = new LevelGenerator(600,600, "/Level/level2.txt");
        GameManager gameManager = new GameManager(levelGenerator.getMap());
        Parent root = gameManager.getBuildSceneGame().getSceneGame();
        sceneManager2.getStage().setScene(new Scene(root,600,600));
        PacmanKeyboardController keyboard = (PacmanKeyboardController) levelGenerator.getPacman().getControllerComponent();
        sceneManager2.setRoot(gameManager.getBuildSceneGame().getSceneGame());
        sceneManager2.getStage().getScene().setOnKeyPressed(keyboard.getEventHandler());
        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 10e+9;//1000000000.0;
                moteur.core_kernel.Timer.getInstance().setTime(t);

                gameManager.update();
            }
        }.start();

        Image coeur = new Image("/Image/Coeur.png",25,25,false,false);


        ImageUI vie1 =new ImageUI(600,600,new moteur.physics.Position(30,0));
        ImageUI vie2 =new ImageUI(600,600,new moteur.physics.Position(60,0));
        ImageUI vie3 =new ImageUI(600,600,new moteur.physics.Position(90,0));

        vie1.drawImage(coeur);
        vie2.drawImage(coeur);
        vie3.drawImage(coeur);

        TextUI textUI = new TextUI(600,600,new moteur.physics.Position(450,20));
        textUI.drawText("Score : 0",Color.WHITE,15);

        TextUI textUI1 = new TextUI(600,600,new moteur.physics.Position(250,20));
        textUI1.drawText("Level : 1",Color.WHITE,20);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(textUI.getCanvas(),textUI1.getCanvas(),vie1.getCanvas(),vie2.getCanvas(),vie3.getCanvas());
        sceneManager2.getStage().getScene().setRoot(stackPane);
        sceneManager2.show(gameManager.getBuildSceneGame().getSceneGame());

    }
}


