package gameplay.scene;

import moteur.ui.*;

public class OptionsView extends ViewFX {

    public OptionsView(double height, double width) {
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

        LabelUI labelTitre = new LabelUI("Aide", getWidthScene() * 0.4, 9);
        labelTitre.changeFont(getClass().getResourceAsStream("/Font/ARCADE_N.TTF"),15);
        labelTitre.setColor(Color.WHITE);

        LabelUI labelExplication = new LabelUI("Bienvenue dans PacNoel,\nvotre but faire un maximum de points\nen ramassant les sucres d'orges tout en\nevitant les fantomes.\nBonne chance !", 10, getHeightScene() * 0.2);
        labelExplication.changeFont(getClass().getResourceAsStream("/Font/ARCADE_N.TTF"),11);
        labelExplication.setColor(Color.WHITE);

        addToScene(retour.getButton());
        addToScene(labelTitre.getLabel());
        addToScene(labelExplication.getLabel());
    }
}
