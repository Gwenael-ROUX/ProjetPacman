package Generique.gameplay;

import Generique.gameplay.builder.PacmanBuilder;
import Generique.gameplay.builder.ghost.GhostBlueBuilder;
import Generique.gameplay.builder.ghost.GhostGreenBuilder;
import Generique.gameplay.builder.ghost.GhostRedBuilder;
import Generique.gameplay.builder.ghost.GhostYellowBuilder;
import Generique.gameplay.builder.object.CeriseBuilder;
import Generique.gameplay.builder.object.GommeBuilder;
import Generique.gameplay.builder.object.WallBuilder;
import Generique.moteur.ai.MapRepresentation;
import Generique.moteur.core_kernel.Director;
import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.Map;
import Generique.moteur.physics.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LevelGenerator {
    private Entity[][] matrix;
    private double v1,v2;
    private double dimCaseLong;
    private double dimCaseLarg;
    private MapRepresentation mapRepresentation;

    public LevelGenerator(double v1, double v2, String chemin) {
        this.v1 = v1;
        this.v2 = v2;

        this.mapRepresentation = new MapRepresentation(new Map(matrix));
    }

    public void readFile(String chemin){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(LevelGenerator.class.getResourceAsStream(chemin)));

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

        Director director = new Director(dimCaseLong, dimCaseLarg);
        for (String str : tab){
            double posX = (dimCaseLong * j);
            switch (str) {
                case "#" :
                    director.constructEntity(new WallBuilder(), new Position(posX,posY));
                    break;
                case "p" :
                    director.constructEntity(new PacmanBuilder(), new Position(posX,posY));
                    break;
                case "r" :
                    director.constructEntity(new GhostRedBuilder(), new Position(posX,posY));
                    break;
                case "g" :
                    director.constructEntity(new GhostGreenBuilder(), new Position(posX,posY));
                    break;
                case "y" :
                    director.constructEntity(new GhostYellowBuilder(), new Position(posX,posY));
                    break;
                case "b" :
                    director.constructEntity(new GhostBlueBuilder(), new Position(posX,posY));
                    break;
                case "c" :
                    director.constructEntity(new CeriseBuilder(), new Position(posX,posY));
                    break;
                case "." :
                    director.constructEntity(new GommeBuilder(), new Position(posX,posY));
                    break;
                case "*" :
                    break;
                default:
                    break;
            }
            ++j;
        }
    }

    public void setMatrix(int i, int j, Entity entity) { this.matrix[i][j] = entity; }
    public MapRepresentation getMapRepresentation(){ return mapRepresentation; }
}
