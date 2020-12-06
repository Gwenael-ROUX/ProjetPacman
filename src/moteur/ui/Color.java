package moteur.ui;


public enum Color {
    RED(javafx.scene.paint.Color.RED), BLUE(javafx.scene.paint.Color.BLUE), GREEN(javafx.scene.paint.Color.GREEN), DARK(javafx.scene.paint.Color.BLACK), WHITE(javafx.scene.paint.Color.WHITE),
    YELLOW(javafx.scene.paint.Color.YELLOW);

    public final javafx.scene.paint.Color fxcolor;
    private Color(javafx.scene.paint.Color fxcolor){
        this.fxcolor = fxcolor;
    }
}
