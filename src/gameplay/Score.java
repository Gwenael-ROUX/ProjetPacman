package gameplay;

import java.util.prefs.Preferences;

public class Score {

    private final Preferences preferences = Preferences.userRoot().node(Score.class.getName());


    public String getScorefile(){
        String score = preferences.get("Score", "0");
        return score;
    }

    public void setScorefile(String score)  {
        preferences.put("Score",score);
    }
}
