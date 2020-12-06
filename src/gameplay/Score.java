package gameplay;

import java.util.prefs.Preferences;

public class Score {

    private final Preferences preferences = Preferences.userRoot().node(Score.class.getName());


    public String getScorefile(){
        return preferences.get("Score", "0");
    }

    public void setScorefile(String score)  {
        preferences.put("Score",score);
    }
}
