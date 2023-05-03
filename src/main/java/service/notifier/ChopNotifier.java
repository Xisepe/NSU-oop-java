package service.notifier;

import model.event.player.DrawLumberjackChopEvent;
import model.event.tree.DrawChoppedTreeEvent;
import service.observer.GameObservable;

public class ChopNotifier extends ViewNotifier{

    public ChopNotifier(GameObservable observable) {
        super(observable);
    }

    private void requestDrawChoppedTree() {
        observable.notifyAll(new DrawChoppedTreeEvent());
    }
    private void requestDrawLumberjack() {
        observable.notifyAll(new DrawLumberjackChopEvent());
    }

    @Override
    public void notifyView() {
        requestDrawChoppedTree();
        requestDrawLumberjack();
    }
}
