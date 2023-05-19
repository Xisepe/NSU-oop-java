package view.game;

import model.event.GameEvent;
import model.event.player.ScoreUpdateEvent;
import model.state.score.Score;
import service.observer.GameObserver;

import javax.swing.*;
import java.awt.*;


public class ScoreView extends JLabel implements GameObserver {
    private final Score score;

    public ScoreView(Score score) {
        this.score = score;
        setVisible(true);
        setText("Score "+ score.getValue());
        setForeground(Color.WHITE);
    }

    public void buildScoreLabel() {
        setText(String.format("Score %d", score.getValue()));
    }

    @Override
    public void notify(GameEvent event) {
        if (event instanceof ScoreUpdateEvent) {
            buildScoreLabel();
        }
    }
}
