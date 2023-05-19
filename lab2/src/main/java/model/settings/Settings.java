package model.settings;

import lombok.Data;

@Data
public final class Settings {
    private VideoSettings videoSettings;
    private VolumeSettings volumeSettings;
    private KeyboardSettings keyboardSettings;
}
