import javax.sound.sampled.*;
import java.io.IOException;

public class Sound extends Thread {
    private AudioListener listener = new AudioListener();
    private Clip clip;

    Sound(String soundName) {
        super();
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream("/Sound/" + soundName))) {
            this.clip = AudioSystem.getClip();
            this.clip.addLineListener(listener);
            this.clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void play() {
        try {
            clip.start();
            listener.waitUntilDone();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            clip.close();
        }
    }

    public void stopSound() {
        clip.close();
        clip.stop();
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    @Override
    public void run() {
        this.play();
    }
}
