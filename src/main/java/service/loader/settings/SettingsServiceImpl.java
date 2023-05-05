package service.loader.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import model.settings.Settings;


import java.io.File;

public class SettingsServiceImpl implements SettingsService {
    private final String SETTINGS_PATH = "settings/";
    private final ObjectMapper mapper = new ObjectMapper();

    private static SettingsServiceImpl INSTANCE;

    public synchronized static SettingsServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SettingsServiceImpl();
        }
        return INSTANCE;
    }

    private SettingsServiceImpl() {
    }

    @SneakyThrows
    @Override
    public void save(Settings settings, String filename) {
        File file = new File(SETTINGS_PATH, filename);
        if (!file.exists()) {
            boolean created = file.createNewFile();
        }
        mapper.writeValue(file, settings);
    }

    @SneakyThrows
    @Override
    public Settings load(String name) {
        File settingsFile = new File(SETTINGS_PATH, name);
        return mapper.readValue(settingsFile, Settings.class);
    }
}
