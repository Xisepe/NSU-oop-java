package model.asset;

import lombok.Data;
import model.sound.Sound;

@Data
public class GameSoundData {
    private final Sound background;
    private final Sound hoverEffect;
    private final Sound chopEffect;
    private final Sound gameOver;
}
