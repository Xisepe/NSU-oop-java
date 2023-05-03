package model.settings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class VideoSettings {
    private int currentSettings;
    private ScreenResolution[] available;
    @JsonIgnore
    public ScreenResolution getCurrentScreenResolution() {
        return available[currentSettings];
    }
}
