package gameplay;

public enum EntityType {
    WALL("wall"),
    GOMME("gomme"),
    CERISE("cerise"),
    TREE("tree"),
    PACMAN("pacman"),
    GHOST("ghost");

    public final String name;
    private EntityType(String name){
        this.name = name;
    }
}
