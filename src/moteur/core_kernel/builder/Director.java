package moteur.core_kernel.builder;

import moteur.physics.Position;

/**
 * Classe construisant les entity
 */
public class Director {
    //correspond à la longueur d'une case
    private final double dimCaseLong;
    //correspond à la largeur d'une case
    private final double dimCaseLarg;

    public Director(double dimCaseLong, double dimCaseLarg) {
        this.dimCaseLong = dimCaseLong;
        this.dimCaseLarg = dimCaseLarg;
    }


    // Méthode qui prends un entity builder et qui appelle ses différentes méthodes pour construire l'entity correspondantes
    public void constructEntity (EntityBuilder entityBuilder, Position position){
        entityBuilder.createEntity();
        entityBuilder.buildPosition(position);
        entityBuilder.buildName();
        entityBuilder.buildOrientation();
        entityBuilder.buildContComp();
        entityBuilder.buildPhysComp(dimCaseLong, dimCaseLarg);
        entityBuilder.buildGraphComp(dimCaseLong, dimCaseLarg);
    }
}
