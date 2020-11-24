import javax.sound.sampled.*;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        playClip("/pacman_chomp.wav");
        playClip("/pacman_beginning.wav");

    }

    private static synchronized void playClip(String clipFile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AudioListener listener = new AudioListener();
                try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream("/Sound/" + clipFile))) {
                    Clip clip = AudioSystem.getClip();
                    try (clip) {
                        clip.addLineListener(listener);
                        clip.open(audioInputStream);
                        clip.start();
                        listener.waitUntilDone();
                    }
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
