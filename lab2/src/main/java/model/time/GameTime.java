package model.time;

import lombok.Getter;

import javax.swing.*;

public class GameTime {
    private static final int DELAY = 1000;
    private static final int GAME_SECONDS = 60;
    @Getter
    private int elapsed;
    private int lastTime;
    private final Timer timer = new Timer(DELAY, (e) -> {
        elapsed++;
    });

    public int getReminder() {
        return GAME_SECONDS - elapsed;
    }

    public boolean isTimeChanged() {
        boolean res = lastTime != elapsed;
        if (res) {
            lastTime = elapsed;
        }
        return res;
    }

    public boolean isTimeEnded() {
        return elapsed >= GAME_SECONDS;
    }

    public void drop() {
        elapsed = 0;
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
