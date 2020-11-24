import javax.sound.sampled.*;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        SoundManager.getInstance().addSound("pacman_chomp.wav", "manger", false);
        SoundManager.getInstance().addSound("pacman_beginning.wav", "debut", false);
        System.out.println(SoundManager.getInstance().getCurrentSounds());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(SoundManager.getInstance().getCurrentSounds());
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
