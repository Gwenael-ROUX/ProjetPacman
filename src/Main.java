import moteur.sound.SoundManager;

public class Main{
    public static void main(String[] args) {
        SoundManager.getInstance().addSound("pacman_chomp.wav", "manger", false, 0.5f);
        SoundManager.getInstance().addSound("pacman_beginning.wav", "debut", true,0.5f);

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
