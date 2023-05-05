package view.components.button;

import model.event.game.changescreen.OpenSettingsEvent;
import service.observer.GameObservable;

import java.awt.event.ActionEvent;

public class OpenSettingsButton extends GameButton{
    public static final String TITLE = "SETTINGS";

    public OpenSettingsButton(GameObservable observable) {
        super(TITLE, observable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        observable.notifyAll(new OpenSettingsEvent());
    }
}
