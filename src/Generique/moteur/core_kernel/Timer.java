package Generique.moteur.core_kernel;

public class Timer {
    private static Timer instance;
    private double t = 0;

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