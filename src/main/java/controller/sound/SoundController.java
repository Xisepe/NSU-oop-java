package controller.sound;

import lombok.RequiredArgsConstructor;
import model.asset.GameSoundData;
import model.event.GameEvent;
import model.settings.VolumeSettings;
import service.observer.GameObserver;

@RequiredArgsConstructor
public class SoundController implements GameObserver {
    private final GameSoundData soundData;
    private final VolumeSettings volumeSettings;

    public void updateVolume() {
        soundData.getChopEffect().setVolume(
                volumeSettings.getEffectsPercentage()
        );
        soundData.getGameOver().setVolume(
                volumeSettings.getEffectsPercentage()
        );
        soundData.getHoverEffect().setVolume(
                volumeSettings.getEffectsPercentage()
        );
        soundData.getBackground().setVolume(
                volumeSettings.getBackgroundMusicPercentage()
        );
    }

    public void playBackground() {
        soundData.getBackground().setLoop(true);
    }

    @Override
    public void notify(GameEvent event) {

    }
}
