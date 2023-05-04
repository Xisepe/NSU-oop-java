package model.sound;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

@RequiredArgsConstructor
public class DefaultSound implements Sound {

    @NonNull
    private final Clip clip;

    @Override
    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    @Override
    public void stop() {
        clip.stop();
    }

    @Override
    public void setLoop(boolean loop) {
        clip.loop(loop ? Clip.LOOP_CONTINUOUSLY : 0);
    }

    @Override
    public void setVolume(int percent) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * percent / 100.0f) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
}
