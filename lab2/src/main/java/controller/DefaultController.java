package controller;

import model.event.GameEvent;
import service.observer.GameObservable;
import service.observer.GameObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultController implements GameObservable, Controller {
    private final List<GameObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAll(GameEvent event) {
        observers.forEach(o->o.notify(event));
    }
}
