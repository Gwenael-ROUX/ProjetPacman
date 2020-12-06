package moteur.ui;

import javafx.event.ActionEvent;

public class SceneAction {
    private ActionEvent action;

    public SceneAction(ActionEvent action){
        this.action = action;
    }

    public ActionEvent getAction(){
        return action;
    }
}
