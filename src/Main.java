import javax.sound.sampled.*;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        try {
            playClip("/pacman_chomp.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void playClip(String clipFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        AudioListener listener = new AudioListener();
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream("/Sound/" + clipFile))) {
            Clip clip = AudioSystem.getClip();
            try (clip) {
                clip.addLineListener(listener);
                clip.open(audioInputStream);
                clip.start();
                listener.waitUntilDone();
            }
        }
    }
}
