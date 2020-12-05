package gameplay;

import gameplay.ai.RandomShortestPathAI;
import gameplay.ai.ShortestPathAI;
import gameplay.ai.SmartShortestPathAI;
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
import java.util.HashMap;
import java.util.List;

public class LevelGenerator {
    private Entity pacman;
    private Entity ghost;
    private List<Entity>[][] matrix;
    private double v1,v2;
    private double dimCaseLong;
    private double dimCaseLarg;
    private Map map;
    private ShortestPathAI shortestPathAI;
    private RandomShortestPathAI randomShortestPathAI;
    private SmartShortestPathAI smartShortestPathAI;
    private HashMap<Entity, Position> initPositionEntities;
    private boolean twoPlayer;

    public LevelGenerator(double v1, double v2, String chemin, boolean twoPlayer) {
        this.v1 = v1;
        this.v2 = v2;
        this.twoPlayer = twoPlayer;
        initPositionEntities = new HashMap<>();
        BasicPathFinder basicPathFinder = new BasicPathFinder(Arrays.asList(EntityType.GOMME.name, EntityType.CERISE.name));
        shortestPathAI = new ShortestPathAI();
        randomShortestPathAI = new RandomShortestPathAI();
        smartShortestPathAI = new SmartShortestPathAI();
        shortestPathAI.setPathFinder(basicPathFinder);
        randomShortestPathAI.setPathFinder(basicPathFinder);
        smartShortestPathAI.setPathFinder(basicPathFinder);
        map = new Map(new Position(0,0), new Position(v1, v2));

        readFile(chemin);

        map.setLimitBottomRight(new Position(dimCaseLong*matrix[0].length, dimCaseLarg*matrix.length));
        map.setMatrix(matrix);
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
            dimCaseLong = Math.floor(v1 / nbcaseX);
            dimCaseLarg = Math.floor(v2 / nbcaseY);

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
                    builder = new PacmanBuilder(map);
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    pacman = builder.getEntity();
                    initPositionEntities.put(pacman, new Position(j,i));

                    shortestPathAI.setTarget(pacman);
                    randomShortestPathAI.setTarget(pacman);
                    smartShortestPathAI.setTarget(pacman);
                    break;
                case "r" :
                    builder = new GhostRedBuilder(shortestPathAI);
                    director.constructEntity(builder, new Position(posX,posY));
                    Entity e = builder.getEntity();
                    setMatrix(i,j, e);
                    initPositionEntities.put(e, new Position(j,i));

                    shortestPathAI.setOrigin(e);
                    break;
                case "g" :
                    builder = new GhostGreenBuilder(map, twoPlayer);
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    ghost = builder.getEntity();
                    initPositionEntities.put(ghost, new Position(j,i));
                    break;
                case "y" :
                    builder = new GhostYellowBuilder(smartShortestPathAI);
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    initPositionEntities.put(builder.getEntity(), new Position(j,i));

                    smartShortestPathAI.setOrigin(builder.getEntity());
                    break;
                case "b" :
                    builder = new GhostBlueBuilder(randomShortestPathAI);
                    director.constructEntity(builder, new Position(posX,posY));
                    setMatrix(i,j, builder.getEntity());
                    initPositionEntities.put(builder.getEntity(), new Position(j,i));

                    randomShortestPathAI.setOrigin(builder.getEntity());
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
                case "s" :
                    builder = new XMasTreeBuilder();
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

    public HashMap<Entity, Position> getInitPositionEntities(){
        return initPositionEntities;
    }
}
