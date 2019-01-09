package Card_games.BlackJack_FX.Controlers;

import javafx.animation.AnimationTimer;

public abstract class AnimationTimerExt extends AnimationTimer {

    private long sleepNs = 0;
    private long prevTime = 0;

    AnimationTimerExt(long sleepMs) {                       //constructor
        this.sleepNs = sleepMs * 1_000_000;
    }

    @Override
    public void handle(long now) {

        // some delay
        if ((now - prevTime) < sleepNs) {
            return;
        }
        prevTime = now;
        handle();
    }

    public abstract void handle();

}