package moteur.sound;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Classe permettant de gérer les son (Singleton)
 */
public class SoundManager {
    private static SoundManager instance;
    // Liste contenant tous les sons en cours
    private ConcurrentLinkedQueue<Sound> currentSounds = new ConcurrentLinkedQueue<>();

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Fonction ajoutant un son aux sons courants
     * @param soundName
     * @param name
     * @param isLoop
     * @param volume
     * @param start
     */
    public void addSound(String soundName, String name, boolean isLoop, float volume, Long start) {
        Sound sound = new Sound(soundName, name, start);
        sound.setVolume(volume);
        sound.setLoop(isLoop);
        sound.start();
        currentSounds.add(sound);
    }

    /**
     * Fonction permettant de stopper un son spécifique
     * @param name
     */
    public void stopASound(String name) {
        System.out.println(currentSounds.size());
        for (Sound sound : currentSounds) {
            if (sound.getSoundName().equals(name)) {
                sound.stopSound();
            }
        }
    }

    /**
     * Fonction supprimant le son de la liste des sons courants
     * @param name
     */
    public void removeSound(String name) {
        currentSounds.removeIf(sound -> sound.getSoundName().equals(name));
    }

    /**
     * Fonction permettant de stopper tous les sons en cours
     */
    public void stopAllSound() {
        for (Sound sound : currentSounds) {
            sound.stopSound();
        }
    }

    public ConcurrentLinkedQueue<Sound> getCurrentSounds() {
        return currentSounds;
    }
}
