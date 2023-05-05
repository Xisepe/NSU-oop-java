package view.game;

import model.asset.LumberjackViewData;
import model.event.GameEvent;
import model.event.player.DrawChopLumberjackEvent;
import model.event.player.DrawStandLumberjackEvent;
import model.event.player.DrawSwingLumberjackEvent;
import model.event.player.LumberjackEvent;
import model.player.Lumberjack;
import model.state.Position;
import view.DefaultView;

import java.awt.*;
import java.awt.image.BufferedImage;


public class LumberjackView extends DefaultView {

    private final Lumberjack player;
    private final LumberjackViewData viewData;

    public LumberjackView(BufferedImage buffer, Lumberjack player, LumberjackViewData viewData) {
        super(buffer);
        this.player = player;
        this.viewData = viewData;
    }

    private void drawLeft(Graphics2D g, BufferedImage image) {
        g.drawImage(
                image,
                viewData.getXOffset(),
                viewData.getYOffset(),
                null
        );
    }

    private void drawRight(Graphics2D g, BufferedImage image) {
        g.drawImage(
                image,
                buffer.getWidth() - viewData.getStandRight().getWidth() - viewData.getXOffset(),
                viewData.getYOffset(),
                null
        );
    }

    private void drawStand() {
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        if (player.getPlayerPosition() == Position.LEFT) {
            drawLeft(g, viewData.getStandLeft());
        } else {
            drawRight(g, viewData.getStandRight());
        }
    }

    private void drawSwing() {
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        if (player.getPlayerPosition() == Position.LEFT) {
            drawLeft(g, viewData.getSwingLeft());
        } else {
            drawRight(g, viewData.getSwingRight());

        }
    }

    private void drawChop() {
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        if (player.getPlayerPosition() == Position.LEFT) {
            drawLeft(g, viewData.getChopLeft());
        } else {
            drawRight(g, viewData.getChopRight());
        }
    }

    @Override
    public void notify(GameEvent event) {
        if (!(event instanceof LumberjackEvent)) {
            return;
        }
        if (event instanceof DrawStandLumberjackEvent) {
            drawStand();
        } else if (event instanceof DrawSwingLumberjackEvent) {
            drawSwing();
        } else if (event instanceof DrawChopLumberjackEvent) {
            drawChop();
        }
    }
}
