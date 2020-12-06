package moteur.sound;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Classe permettant d'observer un son en cours (savoir lorqu'il termine)
 */
public class AudioListener implements LineListener{
    private boolean done = false;
    @Override public synchronized void update(LineEvent event) {
        LineEvent.Type eventType = event.getType();
        if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
            done = true;
            notifyAll();
        }
    }

    /**
     * Fonction attendant la fin du son
     * @throws InterruptedException
     */
    public synchronized void waitUntilDone() throws InterruptedException {
        while (!done) { wait(); }
    }
}
