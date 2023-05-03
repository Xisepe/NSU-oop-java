package service.notifier;

import model.event.player.DrawLumberjackStayEvent;
import model.event.tree.DrawTreeEvent;
import service.observer.GameObservable;

public class StayNotifier extends ViewNotifier {
    public StayNotifier(GameObservable observable) {
        super(observable);
    }

    private void requestDrawTree() {
        observable.notifyAll(new DrawTreeEvent());
    }

    private void requestDrawLumberjack() {
        observable.notifyAll(new DrawLumberjackStayEvent());
    }

    @Override
    public void notifyView() {
        requestDrawTree();
        requestDrawLumberjack();
    }
}
