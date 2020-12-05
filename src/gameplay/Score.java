package gameplay;

import java.io.*;

public class Score {

    private static final String SCOREFILE = "/Score/score.txt";
    private static final String SCOREFILE2 = "Ressources/Score/score.txt";


    public String getScorefile(){
        String score = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Score.class.getResourceAsStream(SCOREFILE)));
            score = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  score;
    }

    public void setScorefile(String score)  {
        try {
            File file = new File(SCOREFILE2);
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(score);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
