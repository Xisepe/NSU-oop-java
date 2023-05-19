package view.game;

import model.event.GameEvent;
import model.event.player.TimeUpdateEvent;
import model.time.GameTime;
import service.observer.GameObserver;

import javax.swing.*;
import java.awt.*;

public class TimerView extends JLabel implements GameObserver {
    private final GameTime gameTime;

    public TimerView(GameTime gameTime) {
        this.gameTime = gameTime;
        setForeground(Color.WHITE);
    }

    public void updateTimeLabel() {
        setText("Time " + gameTime.getReminder());
    }

    @Override
    public void notify(GameEvent event) {
        if (event instanceof TimeUpdateEvent) {
            updateTimeLabel();
        }
    }
}
