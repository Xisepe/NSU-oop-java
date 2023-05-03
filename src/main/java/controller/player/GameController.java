package controller.player;

import controller.DefaultController;
import lombok.RequiredArgsConstructor;
import model.event.GameOverEvent;
import model.player.Lumberjack;
import model.tree.Tree;
import service.notifier.ChopNotifier;
import service.notifier.StayNotifier;
import service.notifier.ViewNotifier;

@RequiredArgsConstructor
public class GameController extends DefaultController {
    private final KeyboardController keyboardController;
    private final Lumberjack player;
    private final Tree tree;
    private boolean available;

    private final ViewNotifier chopNotifier = new ChopNotifier(this);
    private final ViewNotifier stayNotifier = new StayNotifier(this);

    @Override
    public void update() {
        if (!available || !isChop()) {
            stay();
            return;
        }
        movePlayer();
        chop();
    }

    public void chop() {
        available = false;
        player.chop(tree);
        if (isDead()) {
            notifyAll(new GameOverEvent());
        }
        requestChopViewUpdate();
    }

    public void stay() {
        available = true;
        requestStayViewUpdate();
    }

    public boolean isDead() {
        return player.isDead();
    }

    private void requestChopViewUpdate() {
        chopNotifier.notifyView();
    }

    private void requestStayViewUpdate() {
        stayNotifier.notifyView();
    }

    private boolean isChop() {
        return keyboardController.isChopLeft() || keyboardController.isChopRight();
    }

    private void movePlayer() {
        if (keyboardController.isChopRight() && !keyboardController.isChopLeft()) {
            player.moveRight();
        } else if (!keyboardController.isChopRight() && keyboardController.isChopLeft()) {
            player.moveLeft();
        }
    }
}
