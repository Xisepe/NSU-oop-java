package model.settings;

import lombok.Data;

@Data
public class VolumeSettings {
    private boolean enable = true;
    private int backgroundMusicPercentage = 50;
    private int effectsPercentage = 50;
}
