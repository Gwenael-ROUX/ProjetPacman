package Generique;

import Generique.gameplay.LevelGenerator;

public class Main {
    public static void main(String[] args) {
        LevelGenerator levelGenerator = new LevelGenerator(600,600, "/Level/level1.txt");
//        for (int i = 0; i < 10; i++) {
//            System.out.println();
//            for (int j = 0; j < 10; j++) {
//                System.out.print("\t"+levelGenerator.getMapRepresentation().getMap().getEntity(i,j).getName());
//            }
//        }
    }
}
