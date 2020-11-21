package Generique.moteur.core_kernel;

import Generique.moteur.core_kernel.EntityBuilder;
import Generique.moteur.physics.Position;

public class Director {
    private final double dimCaseLong;
    private final double dimCaseLarg;

    public Director(double dimCaseLong, double dimCaseLarg) {
        this.dimCaseLong = dimCaseLong;
        this.dimCaseLarg = dimCaseLarg;
    }


    public void constructEntity (EntityBuilder entityBuilder, Position position){
        entityBuilder.createEntity();
        entityBuilder.buildPosition(position);
        entityBuilder.buildName();
        entityBuilder.buildOrientation();
        entityBuilder.buildContComp();
        entityBuilder.buildPhysComp();
        entityBuilder.buildGraphComp(dimCaseLong, dimCaseLarg);
        entityBuilder.buildSoundComp();
    }
}
