package gameplay;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import moteurs.Entity;
import moteurs.Position;
import moteurs.controllers.KeyboardController;
import moteurs.controllers.ai.AI;
import moteurs.controllers.ai.RandomAI;
import moteurs.physics.BoxCollider;
import moteurs.physics.Physics;

import java.io.*;

public class LevelGenerator {
    private Entity[][] matrix;
    private double v1,v2;
    private double dimCaseLong;
    private double dimCaseLarg;

    public LevelGenerator(double v1, double v2, String chemin) {
        this.v1 = v1;
        this.v2 = v2;
        readFile(chemin);
    }

    public void readFile(String chemin){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    LevelGenerator.class.getResourceAsStream(chemin)));

            String line;
            line = in.readLine();
            String[] arrOfStr = line.split(";");
            int nbcaseX = Integer.parseInt(arrOfStr[0]);
            int nbcaseY = Integer.parseInt(arrOfStr[1]);
            dimCaseLong = v1 / nbcaseX;
            dimCaseLarg = v2 / nbcaseY;

            int i = 0;
            matrix = new Entity[nbcaseX][nbcaseY];
            while ((line = in.readLine()) != null) {
                arrOfStr = line.split("");
                putEntity(arrOfStr, i);
                ++i;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putEntity(String[] tab, int i){
        int j = 0;
        double posY = (dimCaseLarg * i) + (dimCaseLarg/2);
        for (String str:tab){
            double posX = (dimCaseLong * j) + (dimCaseLong/2);
            switch (str) {
                case "#" :
                    break;
                case "p" :
                    ImageView pacmanImageView = new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/Pacman.png")));
                    KeyboardController pacmanController = new KeyboardController();
                    Physics pacmanPhysics = new Physics(10, 1);
                    pacmanImageView.setFitHeight(20);
                    pacmanImageView.setFitWidth(20);
                    pacmanImageView.setX(posX);
                    pacmanImageView.setY(posY);
                    EntityCharacter pacman = new EntityCharacter(new Position(posX, posY),
                            new BoxCollider(new Position(posX-10, posY-10), new Position(posX+10, posY+10)),
                            pacmanImageView, pacmanController, pacmanPhysics, "pacman");
                    setMatrix(i,j, pacman);
                    break;
                case "v" :
                    break;
                case "a" :
                    break;
                case "." :
                    break;
                case "*" :
                    break;
                case "r" :
                    ImageView ghostImageView = new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/GhostRed.png")));
                    AI ghostController = new RandomAI();
                    Physics ghostPhysics = new Physics(10, 1);
                    ghostImageView.setFitHeight(20);
                    ghostImageView.setFitWidth(20);
                    ghostImageView.setX(posX);
                    ghostImageView.setY(posY);

                    EntityCharacter ghost = new EntityCharacter(new Position(posX, posY),
                            new BoxCollider(new Position(posX-10, posY-10),new Position(posX+10, posY+10)),
                            ghostImageView, ghostController, ghostPhysics, "ghost");
                    setMatrix(i,j, ghost);
                    break;
                case "g" :
                    ImageView ghostImageView1 = new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/GhostGreen.png")));
                    AI ghostController1 = new RandomAI();
                    Physics ghostPhysics1 = new Physics(10, 1);
                    ghostImageView1.setFitHeight(20);
                    ghostImageView1.setFitWidth(20);
                    ghostImageView1.setX(posX);
                    ghostImageView1.setY(posY);

                    EntityCharacter ghost1 = new EntityCharacter(new Position(posX, posY),
                            new BoxCollider(new Position(posX-10, posY-10),new Position(posX+10, posY+10)),
                            ghostImageView1, ghostController1, ghostPhysics1, "ghost1");
                    setMatrix(i,j, ghost1);
                    break;
                case "h" :
                    break;
                case "j" :
                    break;
                default:
                    break;
            }
            ++j;
        }
    }

    public Entity[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int i, int j, Entity entity) {
        this.matrix[i][j] = entity;
    }

}
