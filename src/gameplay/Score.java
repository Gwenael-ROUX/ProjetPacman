package gameplay;

import java.io.*;

public class Score {

    private static final String SCOREFILE = "Ressources/Score/score.txt";


    public static String getScorefile(){
        String score = "";
        try {
            InputStream inputStream = new FileInputStream(SCOREFILE);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            score = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  score;
    }

    public static void setScorefile(String score) throws IOException {

        File file = new File(SCOREFILE);
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(score);
        bw.close();
    }
}
