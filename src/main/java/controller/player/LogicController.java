package controller.player;

import controller.DefaultController;
import lombok.RequiredArgsConstructor;
import model.event.game.changescreen.GameOverEvent;
import model.event.background.DrawBackgroundEvent;
import model.event.player.DrawChopLumberjackEvent;
import model.event.player.DrawStandLumberjackEvent;
import model.event.player.DrawSwingLumberjackEvent;
import model.event.sound.effects.PlayChopEffectSoundEvent;
import model.event.sound.effects.PlayGameOverEffectSoundEvent;
import model.event.tree.DrawAllTreeEvent;
import model.event.tree.DrawChoppedTreeEvent;
import model.player.Lumberjack;
import model.state.Action;
import model.state.score.Score;
import model.tree.Tree;

@RequiredArgsConstructor
public class LogicController extends DefaultController {
    private final KeyboardController keyboardController;
    private final Lumberjack player;
    private final Tree tree;
    private final Score score;

    @Override
    public void update() {
        switch (player.getAction()) {
            case STAND: {
                if (!isChop()) {
                    return;
                }
                movePlayer();
                swing();
                drawSwing();
                break;
            }
            case SWING: {
                chop();
                if (isDead()) {
                    gameOver();
                    playGameOverEffectSound();
                    return;
                }
                updateScore();
                drawChop();
                playChopEffectSound();
                break;
            }
            case CHOP: {
                stand();
                drawStand();
                break;
            }
        }

    }

    private void movePlayer() {
        if (keyboardController.isChopRight() && !keyboardController.isChopLeft()) {
            player.moveRight();
        } else if (!keyboardController.isChopRight() && keyboardController.isChopLeft()) {
            player.moveLeft();
        }
    }

    private void swing() {
        player.setAction(Action.SWING);
    }

    private void drawSwing() {
        drawBackground();
        drawAllTree();
        drawSwingLumberjack();
    }

    private void drawSwingLumberjack() {
        notifyAll(new DrawSwingLumberjackEvent());
    }

    private boolean isChop() {
        return keyboardController.isChopLeft() || keyboardController.isChopRight();
    }

    private void chop() {
        player.setAction(Action.CHOP);
        player.chop(tree);
    }

    private void updateScore() {
        score.increment();
    }

    private void drawChop() {
        drawBackground();
        drawChoppedTree();
        drawChopLumberjack();
    }

    private void playChopEffectSound() {
        notifyAll(new PlayChopEffectSoundEvent());
    }

    private void stand() {
        player.setAction(Action.STAND);
    }

    private void drawStand() {
        drawBackground();
        drawAllTree();
        drawStandLumberjack();
    }

    public boolean isDead() {
        return player.isDead();
    }

    private void gameOver() {
        notifyAll(new GameOverEvent());
    }

    private void playGameOverEffectSound() {
        notifyAll(new PlayGameOverEffectSoundEvent());
    }

    private void drawBackground() {
        notifyAll(new DrawBackgroundEvent());
    }

    private void drawChoppedTree() {
        notifyAll(new DrawChoppedTreeEvent());
    }

    private void drawAllTree() {
        notifyAll(new DrawAllTreeEvent());
    }

    private void drawChopLumberjack() {
        notifyAll(new DrawChopLumberjackEvent());
    }

    private void drawStandLumberjack() {
        notifyAll(new DrawStandLumberjackEvent());
    }
}
