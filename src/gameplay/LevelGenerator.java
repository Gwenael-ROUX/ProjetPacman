package gameplay;

import gameplay.ai.ShortestPathAI;
import gameplay.builder.*;
import gameplay.builder.object.*;
import gameplay.builder.ghost.*;
import moteur.ai.BasicPathFinder;
import moteur.core_kernel.builder.*;
import moteur.ai.MapRepresentation;
import moteur.core_kernel.Entity;
import moteur.core_kernel.Map;
import moteur.physics.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelGenerator {
    private Entity pacman;
    private Entity ghost;
    private List<Entity>[][] matrix;
    private double v1,v2;
    private double dimCaseLong;
    private double dimCaseLarg;
    private Map map;
    private BasicPathFinder basicPathFinder;

    public LevelGenerator(double v1, double v2, String chemin) {
        this.v1 = v1;
        this.v2 = v2;
        basicPathFinder = new BasicPathFinder(Arrays.asList(EntityType.GOMME.name, EntityType.CERISE.name));

        readFile(chemin);

        this.map = new Map(matrix, new Position(0,0), new Position(v1, v2));
        MapRepresentation mapRepresentation = new MapRepresentation(map);
        basicPathFinder.setMap(mapRepresentation);
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
            matrix = new ArrayList[nbcaseY][nbcaseX];
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
            matrix[i][j] = new ArrayList<>();
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
                    ShortestPathAI shortestPathAI = new ShortestPathAI();
                    builder = new GhostRedBuilder(shortestPathAI);
                    director.constructEntity(builder, new Position(posX,posY));
                    Entity e = builder.getEntity();
                    setMatrix(i,j, e);

                    shortestPathAI.setPathFinder(basicPathFinder);
                    shortestPathAI.setTarget(pacman);
                    break;
                case "g" :
                    builder = new GhostGreenBuilder();
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    ghost = builder.getEntity();
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
                    director.constructEntity(builder, new Position(posX ,posY));
                    setMatrix(i,j, builder.getEntity());
                    break;
                case " " :
                    break;
            }
            ++j;
        }
    }

    public void setMatrix(int i, int j, Entity entity) { this.matrix[i][j].add(entity); }

    public Map getMap(){ return map; }

    public Entity getPacman() {
        return pacman;
    }

    public Entity getGhost(){
        return ghost;
    }
}
