package Generique.gameplay;

import Generique.gameplay.builder.*;
import Generique.gameplay.builder.object.*;
import Generique.gameplay.builder.ghost.*;
import Generique.moteur.core_kernel.builder.*;
import Generique.moteur.ai.MapRepresentation;
import Generique.moteur.core_kernel.Entity;
import Generique.moteur.core_kernel.Map;
import Generique.moteur.physics.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LevelGenerator {
    private Entity pacman;
    private Entity[][] matrix;
    private double v1,v2;
    private double dimCaseLong;
    private double dimCaseLarg;
    private MapRepresentation mapRepresentation;

    public LevelGenerator(double v1, double v2, String chemin) {
        this.v1 = v1;
        this.v2 = v2;
        readFile(chemin);
        this.mapRepresentation = new MapRepresentation(new Map(matrix, new Position(0,0), new Position(v1, v2)));
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
        EntityBuilder builder;
        for (String str : tab){
            double posX = (dimCaseLong * j);
            switch (str) {
                case "#" :
                    builder = new WallBuilder();
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    break;
                case "p" :
                    builder = new PacmanBuilder();
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    pacman = builder.getEntity();
                    break;
                case "r" :
                    builder = new GhostRedBuilder();
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    break;
                case "g" :
                    builder = new GhostGreenBuilder();
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    break;
                case "y" :
                    builder = new GhostYellowBuilder();
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    break;
                case "b" :
                    builder = new GhostBlueBuilder();
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    break;
                case "c" :
                    builder = new CeriseBuilder();
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    break;
                case "." :
                    builder = new GommeBuilder();
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    break;
            }
            ++j;
        }
    }

    public void setMatrix(int i, int j, Entity entity) { this.matrix[j][i] = entity; }
    public MapRepresentation getMapRepresentation(){ return mapRepresentation; }

    public Entity getPacman() {
        return pacman;
    }
}
