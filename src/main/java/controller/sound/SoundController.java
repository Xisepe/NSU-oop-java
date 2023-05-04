package controller.sound;

import lombok.RequiredArgsConstructor;
import model.asset.GameSoundData;
import model.event.GameEvent;
import model.event.sound.SoundEvent;
import model.event.sound.background.PlayBackgroundMusicSoundEvent;
import model.event.sound.background.StopBackgroundMusicSoundEvent;
import model.event.sound.config.UpdateVolumeSoundEvent;
import model.event.sound.effects.PlayChopEffectSoundEvent;
import model.event.sound.effects.PlayHoverEffectSoundEvent;
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

    public void stopBackground() {
        soundData.getBackground().stop();
    }

    public void playHoverEffect() {
        soundData.getHoverEffect().play();
    }

    public void playGameOverEffect() {
        soundData.getGameOver().play();
    }

    public void playChopEffect() {
        soundData.getChopEffect().play();
    }

    @Override
    public void notify(GameEvent event) {
        if (!(event instanceof SoundEvent)) {
            return;
        }
        if (event instanceof UpdateVolumeSoundEvent) {
            updateVolume();
        } else if (event instanceof PlayChopEffectSoundEvent) {
            playChopEffect();
        } else if (event instanceof PlayBackgroundMusicSoundEvent) {
            playBackground();
        } else if (event instanceof StopBackgroundMusicSoundEvent) {
            stopBackground();
        } else if (event instanceof PlayHoverEffectSoundEvent) {
            playHoverEffect();
        } else {
            playGameOverEffect();
        }
    }
}
