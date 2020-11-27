package moteur.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class Sound extends Thread {
    private AudioListener listener = new AudioListener();
    private Clip clip;
    private boolean isLoop = false;
    private String name;

    Sound(String soundName, String name) {
        super();
        this.name = name;
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/Sound/" + soundName))) {
            this.clip = AudioSystem.getClip();
            this.clip.addLineListener(listener);
            this.clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void play() {
        try {
            if (isLoop)
                clip.loop(Integer.MAX_VALUE);
            clip.start();
            listener.waitUntilDone();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            clip.close();
            SoundManager.getInstance().removeSound(this.name);
        }
    }

    public synchronized void stopSound() {
        clip.setMicrosecondPosition(clip.getMicrosecondLength());
        if (isLoop)
            clip.close();
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

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    public String getSoundName() {
        return name;
    }

    @Override
    public void run() {
        this.play();
    }
}
