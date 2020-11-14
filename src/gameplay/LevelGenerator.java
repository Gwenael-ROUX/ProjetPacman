package gameplay;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import moteurs.Entity;
import moteurs.Position;
import moteurs.controllers.KeyboardController;
import moteurs.controllers.ai.*;
import moteurs.physics.BoxCollider;
import moteurs.physics.Physics;

import java.io.*;

public class LevelGenerator {
    private Entity[][] matrix;
    private double v1,v2;
    private double dimCaseLong;
    private double dimCaseLarg;
    private ShortestPathAI shortestPathAI;
    private MapRepresentation mapRepresentation;

    public LevelGenerator(double v1, double v2, String chemin) {
        this.v1 = v1;
        this.v2 = v2;
        mapRepresentation = new MapRepresentation(matrix);
        this.shortestPathAI = new ShortestPathAI(new BasicPathFinder(mapRepresentation));
        readFile(chemin);
        mapRepresentation.update(matrix);
    }

    public MapRepresentation getMapRepresentation(){
        return mapRepresentation;
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

    private void putEntity(String[] tab, int i) {
        int j = 0;
        double posY = (dimCaseLarg * i);
        for (String str : tab){
            double posX = (dimCaseLong * j);
            switch (str) {
                case "#" :
                    ImageView murView = new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/mur.png")));
                    murView.setFitHeight(dimCaseLarg);
                    murView.setFitWidth(dimCaseLong);
                    murView.setX(posX);
                    murView.setY(posY);
                    ImageEntity mur = new ImageEntity(new Position(posX, posY),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)),
                            "mur",
                            false,
                            murView);
                    setMatrix(i,j, mur);
                    break;
                case "p" :
                    ImageView pacmanImageView = new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/Pacman.png")));
                    KeyboardController pacmanController = new KeyboardController();
                    Physics pacmanPhysics = new Physics(60, 1);
                    pacmanImageView.setFitHeight(dimCaseLarg);
                    pacmanImageView.setFitWidth(dimCaseLong);
                    pacmanImageView.setX(posX+dimCaseLong/2);
                    pacmanImageView.setY(posY+dimCaseLarg/2);
                    EntityCharacter pacman = new EntityCharacter(new Position(posX+dimCaseLong/2, posY+dimCaseLarg/2),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)), "pacman",
                            pacmanImageView, pacmanController, pacmanPhysics);
                    setMatrix(i,j, pacman);
                    this.shortestPathAI.setTarget(pacman);
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
                    AI redghostController = shortestPathAI;
                    Physics ghostPhysics = new Physics(60, 1);
                    ghostImageView.setFitHeight(dimCaseLarg);
                    ghostImageView.setFitWidth(dimCaseLong);
                    ghostImageView.setX(posX+dimCaseLong/2);
                    ghostImageView.setY(posY+dimCaseLarg/2);

                    EntityCharacter redghost = new EntityCharacter(new Position(posX+dimCaseLong/2, posY+dimCaseLarg/2),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)), "ghost",
                            ghostImageView, redghostController, ghostPhysics);
                    setMatrix(i,j, redghost);
                    shortestPathAI.setOrigin(redghost);
                    break;
                case "g" :
                    ImageView ghostImageView1 = new ImageView(new Image(LevelGenerator.class.getResourceAsStream("/Image/GhostGreen.png")));
                    AI ghostController1 = new RandomAI();
                    Physics ghostPhysics1 = new Physics(60, 1);
                    ghostImageView1.setFitHeight(dimCaseLarg);
                    ghostImageView1.setFitWidth(dimCaseLong);
                    ghostImageView1.setX(posX+dimCaseLong/2);
                    ghostImageView1.setY(posY+dimCaseLarg/2);

                    EntityCharacter ghost1 = new EntityCharacter(new Position(posX+dimCaseLong/2, posY+dimCaseLarg/2),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)), "ghost1",
                            ghostImageView1, ghostController1, ghostPhysics1);
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
