package service.loader.sound;

import lombok.SneakyThrows;
import model.sound.DefaultSound;
import model.sound.Sound;
import service.loader.ResourceLoader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStream;

public class SoundLoader implements ResourceLoader<Sound> {
    private static final String SOUND_PATH = "sound/";
    private static SoundLoader INSTANCE;

    public static synchronized SoundLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SoundLoader();
        }
        return INSTANCE;
    }

    private SoundLoader() {
    }

    @SneakyThrows
    @Override
    public Sound load(String name) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(SOUND_PATH + name);
        AudioInputStream in = AudioSystem.getAudioInputStream(resourceAsStream);
        Clip clip = AudioSystem.getClip();
        clip.open(in);
        return new DefaultSound(clip);
    }
}