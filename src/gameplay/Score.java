package gameplay;

import java.util.prefs.Preferences;

/**
 * Classe permettant de gerer le meilleur score
 */
public class Score {

    private final Preferences preferences = Preferences.userRoot().node(Score.class.getName());

    /**
     * récupère le meilleur score
     * @return score
     */
    public String getScorefile(){
        return preferences.get("Score", "0");
    }

    /**
     * écrit le meilleur score localment.
     * @param score
     */
    public void setScorefile(String score)  {
        preferences.put("Score",score);
    }
}
