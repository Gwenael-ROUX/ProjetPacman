import java.util.ArrayList;
import java.util.List;

public class SoundManager {
    private static SoundManager instance;
    private List<Sound> currentSounds = new ArrayList<>();

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void addSound(String soundName, String name, boolean isLoop) {
        Sound sound = new Sound(soundName, name);
        sound.setLoop(isLoop);
        sound.start();
        currentSounds.add(sound);
    }

    public void stopASound(String name) {
        for (Sound sound : currentSounds) {
            if (sound.getSoundName().equals(name)) {
                sound.stopSound();
            }
        }
    }

    public void removeSound(String name) {
        currentSounds.removeIf(sound -> sound.getSoundName().equals(name));
    }

    public void stopAllSound() {
        for (Sound sound : currentSounds) {
            sound.stopSound();
        }
    }

    public List<Sound> getCurrentSounds() {
        return currentSounds;
    }
}
