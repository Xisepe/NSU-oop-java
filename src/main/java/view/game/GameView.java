package view.game;

import lombok.RequiredArgsConstructor;
import model.asset.BackgroundViewData;
import model.draw.buffer.DrawableBuffer;
import model.event.GameEvent;
import model.event.background.DrawBackgroundEvent;
import model.state.score.Score;
import service.observer.GameObserver;

import javax.swing.*;
import java.awt.*;

@RequiredArgsConstructor
public class GameView extends JPanel implements GameObserver {
    private final DrawableBuffer buffer;
    private final BackgroundViewData backgroundViewData;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrame(g);
    }

    private void drawFrame(Graphics g) {
        g.drawImage(buffer.getBuffer(), 0, 0, null);
    }

    private void drawBackground() {
        Graphics2D g = (Graphics2D) buffer.getBuffer().getGraphics();
        g.drawImage(backgroundViewData.getBackground(), 0, 0, null);
    }

    @Override
    public void notify(GameEvent event) {
        if (event instanceof DrawBackgroundEvent) {
            drawBackground();
        }
    }
}
