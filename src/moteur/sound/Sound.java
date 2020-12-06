package moteur.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

/**
 * Classe correspondant à un son joué, c'est un thread
 */
public class Sound extends Thread {
    private AudioListener listener = new AudioListener();
    // Attributs permettant de jouer le son
    private Clip clip;
    // Définis si on joue le son en boucle ou non
    private boolean isLoop = false;
    private String name;
    private Long start;

    Sound(String soundName, String name, Long start) {
        super();
        this.name = name;
        this.start = start;
        // initialisation du clip
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/Sound/" + soundName))) {
            this.clip = AudioSystem.getClip();
            this.clip.addLineListener(listener);
            this.clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction permettant de jouer un son
     */
    private void play() {
        try {
            if (isLoop)
                clip.loop(Integer.MAX_VALUE);
            clip.setMicrosecondPosition(start*1000);
            clip.start();
            listener.waitUntilDone();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // On ferme le son
            clip.close();
            // On le retire de la liste
            SoundManager.getInstance().removeSound(this.name);
        }
    }

    /**
     * Fonction permettant de stopper un son
     */
    public synchronized void stopSound() {
        clip.setMicrosecondPosition(clip.getMicrosecondLength());
        if (isLoop)
            clip.close();
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    /**
     * définis le volume du son
     * @param volume
     */
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
