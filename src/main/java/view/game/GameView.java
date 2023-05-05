package view.game;

import lombok.Getter;
import lombok.Setter;
import model.asset.BackgroundViewData;
import model.event.GameEvent;
import model.event.background.DrawBackgroundEvent;
import model.event.gamestate.ScreenResolutionUpdateEvent;
import model.event.gamestate.UpdateScreenEvent;
import model.settings.ScreenResolution;
import service.observer.GameObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends JPanel implements GameObserver {
    @Getter
    @Setter
    private BufferedImage buffer;
    private final BackgroundViewData backgroundViewData;

    public GameView(BufferedImage buffer, BackgroundViewData backgroundViewData, ScreenResolution resolution) {
        this.buffer = buffer;
        this.backgroundViewData = backgroundViewData;
        setPreferredSize(new Dimension(resolution.getWidth(), resolution.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrame(g);
    }

    private void drawFrame(Graphics g) {
        g.drawImage(buffer, 0, 0, null);
    }

    private void drawBackground() {
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        g.drawImage(backgroundViewData.getBackground(), 0, 0, null);
    }

    @Override
    public void notify(GameEvent event) {
        if (event instanceof DrawBackgroundEvent) {
            drawBackground();
        } else if (event instanceof UpdateScreenEvent) {
            repaint();
        }
//        else if (event instanceof ScreenResolutionUpdateEvent) {
//            setPreferredSize(new Dimension(
//                    ((ScreenResolutionUpdateEvent) event).getResolution().getWidth(),
//                    ((ScreenResolutionUpdateEvent) event).getResolution().getHeight()
//            ));
//        }
    }
}
