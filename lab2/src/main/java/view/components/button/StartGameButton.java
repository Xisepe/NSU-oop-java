package view.components.button;

import model.event.game.changescreen.StartGameEvent;
import service.observer.GameObservable;

import java.awt.event.ActionEvent;

public class StartGameButton extends GameButton{
    public static final String TITLE = "START GAME";

    public StartGameButton(GameObservable observable) {
        super(TITLE, observable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        observable.notifyAll(new StartGameEvent());
    }
}
