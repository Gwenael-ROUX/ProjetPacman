package Generique.gameplay;

public enum EntityType {
    WALL("wall"),
    GOMME("gomme"),
    CERISE("cerise"),
    PACMAN("pacman"),
    GHOST("ghost");

    public final String name;
    private EntityType(String name){
        this.name = name;
    }
}
