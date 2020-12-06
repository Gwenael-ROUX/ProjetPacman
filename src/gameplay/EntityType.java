package gameplay;

/**
 * Definition des noms des entites
 */
public enum EntityType {
    WALL("wall"),
    GOMME("gomme"),
    CADEAU("cadeau"),
    TREE("tree"),
    PACMAN("pacman"),
    GHOST("ghost");

    public final String name;
    private EntityType(String name){
        this.name = name;
    }
}
