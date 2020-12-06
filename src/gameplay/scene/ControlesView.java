package gameplay.scene;

import moteur.ui.*;

/**
 * Vue correspondant aux controles
 */
public class ControlesView extends ViewFX {

    public ControlesView(double height, double width) {
        setHeightScene(height);
        setWidthScene(width);
        init();
    }

    @Override
    public void init() {
        setStyle("-fx-background-color: #000000;");
        BoutonUI retour = new BoutonUI("Retour",0,getPrefHeight() * 0.95);
        retour.changeFont(getClass().getResourceAsStream("/Font/ARCADE_N.TTF"),11);
        retour.setColor(Color.WHITE);

        retour.setAction(new SceneHandler() {
            @Override
            public void handle() {
                SceneManager.getInstance().setRoot(new MenuView(getHeightScene(), getWidthScene()));
            }
        });

        LabelUI labelTitre = new LabelUI("Controle", getWidthScene() * 0.4, 9);
        labelTitre.changeFont(getClass().getResourceAsStream("/Font/ARCADE_N.TTF"),15);
        labelTitre.setColor(Color.WHITE);

        LabelUI labelJ1 = new LabelUI("Joueur 1 :\nz - Aller en haut\nq - Aller a gauche\nd - Aller a droite\ns - Aller en bas\n", 10, getHeightScene() * 0.2);
        labelJ1.changeFont(getClass().getResourceAsStream("/Font/ARCADE_N.TTF"),11);
        labelJ1.setColor(Color.WHITE);

        LabelUI labelJ2 = new LabelUI("Joueur 2 :\nFleche du haut - Aller en haut\nFleche de gauche - Aller a gauche\nFleche de droite - Aller a droite\nFleche du bas - Aller en bas\n", 10, getHeightScene() * 0.4);
        labelJ2.changeFont(getClass().getResourceAsStream("/Font/ARCADE_N.TTF"),11);
        labelJ2.setColor(Color.WHITE);

        addToScene(retour.getButton());
        addToScene(labelTitre.getLabel());
        addToScene(labelJ1.getLabel());
        addToScene(labelJ2.getLabel());
    }
}
