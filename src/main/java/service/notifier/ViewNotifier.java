package service.notifier;

import service.observer.GameObservable;
import service.observer.GameObserver;

public abstract class ViewNotifier {
    protected final GameObservable observable;

    public ViewNotifier(GameObservable observable) {
        this.observable = observable;
    }

    public abstract void notifyView();
}
