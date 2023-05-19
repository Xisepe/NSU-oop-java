package controller.settings;

import model.event.GameEvent;
import model.event.game.ResizeViewsGameEvent;
import model.event.game.changescreen.MenuEvent;
import model.event.settings.SaveSettingsEvent;
import model.event.sound.config.UpdateVolumeSoundEvent;
import model.settings.Settings;
import service.loader.settings.SettingsService;
import service.loader.settings.SettingsServiceImpl;
import service.observer.GameObservable;
import service.observer.GameObserver;

public class SettingsController implements GameObserver {
    private static final String FILENAME = "settings.json";
    private final Settings settings;
    private int lastScreenResolution;
    private final SettingsService settingsService = SettingsServiceImpl.getInstance();
    private final GameObservable observable;

    public SettingsController(Settings settings, GameObservable observable) {
        this.settings = settings;
        this.observable = observable;
        lastScreenResolution = settings.getVideoSettings().getCurrentSettings();
    }

    private void saveSettings() {
        settingsService.save(settings, FILENAME);
        observable.notifyAll(new UpdateVolumeSoundEvent());
        if (lastScreenResolution != settings.getVideoSettings().getCurrentSettings()) {
            lastScreenResolution = settings.getVideoSettings().getCurrentSettings();
            observable.notifyAll(new ResizeViewsGameEvent());
        }
        observable.notifyAll(new MenuEvent());
    }

    @Override
    public void notify(GameEvent event) {
        if (event instanceof SaveSettingsEvent) {
            saveSettings();
        }
    }
}
