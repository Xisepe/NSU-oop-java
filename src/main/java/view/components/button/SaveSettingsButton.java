package view.components.button;

import model.event.settings.SaveSettingsEvent;
import service.observer.GameObservable;

import java.awt.event.ActionEvent;

public class SaveSettingsButton extends GameButton {
    public static final String TITLE = "SAVE";
    private final Runnable saveAction;

    public SaveSettingsButton(Runnable saveAction, GameObservable observable) {
        super(TITLE, observable);
        this.saveAction = saveAction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        saveAction.run();
        observable.notifyAll(new SaveSettingsEvent());
    }
}
