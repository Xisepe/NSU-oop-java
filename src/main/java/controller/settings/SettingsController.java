package controller.settings;

import lombok.RequiredArgsConstructor;
import model.event.GameEvent;
import model.event.settings.SaveSettingsEvent;
import model.settings.Settings;
import service.loader.settings.SettingsService;
import service.loader.settings.SettingsServiceImpl;
import service.observer.GameObservable;
import service.observer.GameObserver;

@RequiredArgsConstructor
public class SettingsController implements GameObserver {
    private static final String FILENAME = "settings.json";
    private final Settings settings;
    private int lastScreenResolution = settings.getVideoSettings().getCurrentSettings();
    private final SettingsService settingsService = SettingsServiceImpl.getInstance();
    private final GameObservable observable;

    private void saveSettings() {
        settingsService.save(settings, FILENAME);
        if (lastScreenResolution != settings.getVideoSettings().getCurrentSettings()) {
            lastScreenResolution = settings.getVideoSettings().getCurrentSettings();
            observable.notifyAll();
        }
    }

    @Override
    public void notify(GameEvent event) {
        if (event instanceof SaveSettingsEvent) {
            saveSettings();
        }
    }
}
