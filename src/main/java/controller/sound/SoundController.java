package controller.sound;

import lombok.RequiredArgsConstructor;
import model.asset.GameSoundData;
import model.event.GameEvent;
import model.event.sound.background.PlayBackgroundMusicSoundEvent;
import model.event.sound.background.StopBackgroundMusicSoundEvent;
import model.event.sound.config.UpdateVolumeSoundEvent;
import model.event.sound.effects.PlayChopEffectSoundEvent;
import model.event.sound.effects.PlayGameOverEffectSoundEvent;
import model.event.sound.effects.PlayHoverEffectSoundEvent;
import model.settings.VolumeSettings;
import service.observer.GameObserver;

@RequiredArgsConstructor
public class SoundController implements GameObserver {
    private final GameSoundData soundData;
    private final VolumeSettings volumeSettings;
    private boolean backgroundWasPlaying;
    private boolean backgroundIsStopped = true;

    public void updateVolume() {
        if (backgroundIsStopped && backgroundWasPlaying) {
            playBackground();
        }
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
        backgroundWasPlaying = true;
        backgroundIsStopped = false;
        soundData.getBackground().setLoop(true);
    }

    public void stopBackground() {
        backgroundIsStopped = true;
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
        if (event instanceof UpdateVolumeSoundEvent) {
            updateVolume();
        }
        if (!volumeSettings.isEnable()) {
            stopBackground();
            return;
        }
        if (event instanceof PlayChopEffectSoundEvent) {
            playChopEffect();
        } else if (event instanceof PlayBackgroundMusicSoundEvent) {
            playBackground();
        } else if (event instanceof StopBackgroundMusicSoundEvent) {
            stopBackground();
        } else if (event instanceof PlayHoverEffectSoundEvent) {
            playHoverEffect();
        } else if (event instanceof PlayGameOverEffectSoundEvent) {
            playGameOverEffect();
        }
    }
}
