package moteur.ui;

import javafx.animation.AnimationTimer;

public class AnimationTimerManager {
    AnimationTimer animationTimer;
    long startNanoTime;

    public void startAnimationTimer() {
        animationTimer.start();
    }

    public void stopAnimationTimer() {
        animationTimer.stop();
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }
}
