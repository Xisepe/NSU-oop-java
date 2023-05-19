package service.observer;

import model.event.GameEvent;

public interface GameObserver {
    void notify(GameEvent event);
}
