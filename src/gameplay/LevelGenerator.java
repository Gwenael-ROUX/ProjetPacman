package gameplay;

import gameplay.builder.EntityCharacterBuilder;
import gameplay.builder.MurBuilder;
import gameplay.builder.PacmanBuilder;
import gameplay.builder.Director;
import gameplay.builder.ghost.BlueGhostBuilder;
import gameplay.builder.ghost.GreenGhostBuilder;
import gameplay.builder.ghost.RedGhostBuilder;
import gameplay.builder.ghost.YellowGhostBuilder;
import javafx.scene.image.ImageView;
import moteurs.Entity;
import moteurs.Position;
import moteurs.controllers.KeyboardController;
import moteurs.controllers.ai.*;
import moteurs.physics.BoxCollider;
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

        Director director = new Director();
        for (String str : tab){
            double posX = (dimCaseLong * j);
            switch (str) {
                case "#" :
                    director.setImageEntityBuilder(new MurBuilder(),new Position(posX, posY),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)));
                    director.constructImageEntity();
                    ImageEntity mur = director.getImageEntity();
                    ImageView murView = mur.getImageView();
                    murView.setFitHeight(dimCaseLarg);
                    murView.setFitWidth(dimCaseLong);
                    murView.setX(posX);
                    murView.setY(posY);
                    setMatrix(i,j, mur);
                    break;
                case "p" :
                    director.setEntityCharacterBuilder(new PacmanBuilder(), new Position(posX+dimCaseLong/2, posY+dimCaseLarg/2),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)));
                    director.constructEntityCharacter();
                    EntityCharacter pacman = director.getEntityCharacter();
                    ImageView pacmanImageView = pacman.getImageView();
                    pacmanImageView.setFitHeight(dimCaseLarg);
                    pacmanImageView.setFitWidth(dimCaseLong);
                    pacmanImageView.setX(posX+dimCaseLong/2);
                    pacmanImageView.setY(posY+dimCaseLarg/2);

                    setMatrix(i,j, pacman);
                    shortestPathAI.setTarget(pacman);
                    break;
                case "r" :
                    RedGhostBuilder builder = new RedGhostBuilder();
                    builder.setAI(shortestPathAI);
                    director.setEntityCharacterBuilder(builder, new Position(posX+dimCaseLong/2, posY+dimCaseLarg/2),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)));
                    director.constructEntityCharacter();
                    EntityCharacter ghostR = director.getEntityCharacter();
                    ImageView ghostRImage = ghostR.getImageView();
                    ghostRImage.setFitHeight(dimCaseLarg);
                    ghostRImage.setFitWidth(dimCaseLong);
                    ghostRImage.setX(posX+dimCaseLong/2);
                    ghostRImage.setY(posY+dimCaseLarg/2);

                    setMatrix(i,j, ghostR);
                    shortestPathAI.setOrigin(ghostR);
                    break;
                case "g" :
                    director.setEntityCharacterBuilder(new GreenGhostBuilder(), new Position(posX+dimCaseLong/2, posY+dimCaseLarg/2),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)));
                    director.constructEntityCharacter();
                    EntityCharacter ghostG = director.getEntityCharacter();
                    ImageView ghostGImage = ghostG.getImageView();
                    ghostGImage.setFitHeight(dimCaseLarg);
                    ghostGImage.setFitWidth(dimCaseLong);
                    ghostGImage.setX(posX+dimCaseLong/2);
                    ghostGImage.setY(posY+dimCaseLarg/2);
                    setMatrix(i,j, ghostG);
                    break;
                case "y" :
                    director.setEntityCharacterBuilder(new YellowGhostBuilder(), new Position(posX+dimCaseLong/2, posY+dimCaseLarg/2),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)));
                    director.constructEntityCharacter();
                    EntityCharacter ghostY = director.getEntityCharacter();
                    ImageView ghostYImage = ghostY.getImageView();
                    ghostYImage.setFitHeight(dimCaseLarg);
                    ghostYImage.setFitWidth(dimCaseLong);
                    ghostYImage.setX(posX+dimCaseLong/2);
                    ghostYImage.setY(posY+dimCaseLarg/2);
                    setMatrix(i,j, ghostY);
                    break;
                case "b" :
                    director.setEntityCharacterBuilder(new BlueGhostBuilder(), new Position(posX+dimCaseLong/2, posY+dimCaseLarg/2),
                            new BoxCollider(new Position(posX, posY), new Position(posX+dimCaseLong, posY+dimCaseLarg)));
                    director.constructEntityCharacter();
                    EntityCharacter ghostB = director.getEntityCharacter();
                    ImageView ghostBImage = ghostB.getImageView();
                    ghostBImage.setFitHeight(dimCaseLarg);
                    ghostBImage.setFitWidth(dimCaseLong);
                    ghostBImage.setX(posX+dimCaseLong/2);
                    ghostBImage.setY(posY+dimCaseLarg/2);
                    setMatrix(i,j, ghostB);
                    break;
                case "v" :
                    break;
                case "a" :
                    break;
                case "." :
                    break;
                case "*" :
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
