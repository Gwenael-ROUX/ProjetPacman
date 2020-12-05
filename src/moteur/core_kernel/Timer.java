package moteur.core_kernel;

/**
 * Horloge du jeu
 * Singleton pour le temps de jeu
 */
public class Timer {
    private static Timer instance;
    private double t = 0;

    /**
     * Singleton
     */
    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }

    public double getTime() {
        return t;
    }

    public void setTime(double t) {
        this.t = t;
    }
}