package view;

import lombok.RequiredArgsConstructor;
import model.asset.LumberjackAssets;
import model.event.GameEvent;
import model.event.player.DrawLumberjackChopEvent;
import model.event.player.DrawLumberjackStayEvent;
import model.player.Lumberjack;
import model.state.Position;

import java.awt.*;


@RequiredArgsConstructor
public class LumberjackView extends DefaultView {

    private final Lumberjack player;
    private final LumberjackAssets lumberjackAssets;

    private void drawChop() {
        Graphics2D g2d = (Graphics2D) buffer.getGraphics();
        if (player.getPlayerPosition() == Position.LEFT) {
            g2d.drawImage(
                    lumberjackAssets.getChopLeft(),
                    lumberjackAssets.getXOffsetChop(),
                    lumberjackAssets.getYOffsetChop(),
                    null
            );
        } else {
            g2d.drawImage(
                    lumberjackAssets.getChopRight(),
                    buffer.getWidth() - lumberjackAssets.getXOffsetChop() - lumberjackAssets.getChopRight().getWidth(),
                    lumberjackAssets.getYOffsetChop(),
                    null
            );
        }
    }

    private void drawStay() {
        Graphics2D g2d = (Graphics2D) buffer.getGraphics();
        if (player.getPlayerPosition() == Position.LEFT) {
            g2d.drawImage(
                    lumberjackAssets.getStayLeft(),
                    lumberjackAssets.getXOffsetStay(),
                    lumberjackAssets.getYOffsetStay(),
                    null
            );
        } else {
            g2d.drawImage(
                    lumberjackAssets.getChopRight(),
                    buffer.getWidth() - lumberjackAssets.getXOffsetStay() - lumberjackAssets.getChopRight().getWidth(),
                    lumberjackAssets.getYOffsetStay(),
                    null
            );
        }
    }

    @Override
    public void notify(GameEvent event) {
        if (event instanceof DrawLumberjackChopEvent) {
            drawChop();
        } else if (event instanceof DrawLumberjackStayEvent) {
            drawStay();
        }
    }
}
