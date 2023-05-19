package service.observer;

import model.event.GameEvent;

public interface GameObservable {
    void addObserver(GameObserver observer);
    void removeObserver(GameObserver observer);
    void notifyAll(GameEvent event);
}
