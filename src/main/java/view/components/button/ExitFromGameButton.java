package view.components.button;

import model.event.game.ExitFromGameEvent;
import service.observer.GameObservable;

import java.awt.event.ActionEvent;

public class ExitFromGameButton extends GameButton{
    public static final String TITLE = "EXIT";

    public ExitFromGameButton(GameObservable observable) {
        super(TITLE, observable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        observable.notifyAll(new ExitFromGameEvent());
    }
}
