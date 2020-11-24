import javax.sound.sampled.*;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        SoundManager.getInstance().addSound("pacman_chomp.wav", "manger", false);
        SoundManager.getInstance().addSound("pacman_beginning.wav", "debut", true);
        System.out.println(SoundManager.getInstance().getCurrentSounds());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(SoundManager.getInstance().getCurrentSounds());
        SoundManager.getInstance().stopASound("debut");
        System.out.println(SoundManager.getInstance().getCurrentSounds());
    }
}
