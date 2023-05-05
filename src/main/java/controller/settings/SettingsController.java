package controller.settings;

import lombok.RequiredArgsConstructor;
import model.event.GameEvent;
import model.event.settings.SaveSettingsEvent;
import model.settings.Settings;
import service.loader.settings.SettingsService;
import service.loader.settings.SettingsServiceImpl;
import service.observer.GameObserver;

@RequiredArgsConstructor
public class SettingsController implements GameObserver {
    private static final String FILENAME = "settings.json";
    private final Settings settings;
    private final SettingsService settingsService = SettingsServiceImpl.getInstance();

    private void saveSettings() {
        settingsService.save(settings, FILENAME);
    }

    @Override
    public void notify(GameEvent event) {
        if (event instanceof SaveSettingsEvent) {
            saveSettings();
        }
    }
}
