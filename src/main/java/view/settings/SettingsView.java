package view.settings;

import model.event.GameEvent;
import model.settings.KeyboardSettings;
import model.settings.Settings;
import model.settings.VideoSettings;
import model.settings.VolumeSettings;
import service.observer.GameObservable;
import service.observer.GameObserver;
import view.components.button.SaveSettingsButton;
import view.components.dropmenu.VideoDropMenu;
import view.components.keybinder.KeyBinder;
import view.components.slider.VolumeSlider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;


public class SettingsView extends JPanel implements GameObservable {
    private final java.util.List<GameObserver> observers = new ArrayList<>();
    private final Settings settings;
    //video settings
    private final JLabel videoSettings = new JLabel("Screen Resolution");
    private final VideoDropMenu videoDropMenu;
    //sound settings
    private final JLabel soundSettings = new JLabel("Sound");
    private final VolumeSlider backgroundVolumeSlider;
    private final VolumeSlider effectsVolumeSlider;
    private final JCheckBox disableSounds;
    //key binders
    private final JLabel controls = new JLabel("Controls");
    private final KeyBinder chopLeft;
    private final KeyBinder chopRight;
    //save btn
    private final JButton saveSettingsButton = new SaveSettingsButton(this::onSave, this);

    public SettingsView(Settings settings) {
        this.settings = settings;
        videoDropMenu = initializeVideoDropMenu(settings);
        backgroundVolumeSlider = initializeBackgroundVolumeSlider(settings);
        effectsVolumeSlider = initializeEffectsVolumeSlider(settings);
        disableSounds = initializeDisableSound(settings);
        chopLeft = initializeKeyBinder("Chop Left", settings.getKeyboardSettings().getChopLeft());
        chopRight = initializeKeyBinder("Chop Right", settings.getKeyboardSettings().getChopRight());
        initializeLayout();
    }

    private KeyBinder initializeKeyBinder(String label, int settings) {
        return new KeyBinder(
                label,
                KeyEvent.getKeyText(settings),
                this){{
                    setKeyCode(settings);
        }};
    }

    private JCheckBox initializeDisableSound(Settings settings) {
        final JCheckBox disableSounds;
        disableSounds = new JCheckBox("Disable sounds") {{
            setSelected(!settings.getVolumeSettings().isEnable());
        }};
        return disableSounds;
    }

    private VolumeSlider initializeEffectsVolumeSlider(Settings settings) {
        return new VolumeSlider(
                0, 100,
                settings.getVolumeSettings().getEffectsPercentage(),
                "Effects Volume",
                this
        );
    }

    private VolumeSlider initializeBackgroundVolumeSlider(Settings settings) {
        return new VolumeSlider(
                0, 100,
                settings.getVolumeSettings().getBackgroundMusicPercentage(),
                "Background Volume",
                this
        );
    }

    private VideoDropMenu initializeVideoDropMenu(Settings settings) {
        return new VideoDropMenu(
                settings.getVideoSettings().getAvailable(),
                settings.getVideoSettings().getCurrentSettings(),
                this
        );
    }

    private void initializeLayout() {
        setLayout(new GridLayout(0, 1));
        addVideoSettings();
        addSoundSettings();
        addKeyBinders();
        add(saveSettingsButton);
    }

    private void addSoundSettings() {
        add(soundSettings);
        add(backgroundVolumeSlider);
        add(effectsVolumeSlider);
        add(disableSounds);
    }

    private void addVideoSettings() {
        add(videoSettings);
        add(videoDropMenu);
    }

    private void addKeyBinders() {
        add(controls);
        add(chopLeft);
        add(chopRight);
    }

    private void onSave() {
        updateVideo();
        updateVolume();
        updateKeyBinding();
    }

    private void updateVideo() {
        VideoSettings videoSettings = settings.getVideoSettings();
        videoSettings.setCurrentSettings(videoDropMenu.getSelectedIndex());
    }

    private void updateVolume() {
        VolumeSettings volumeSettings = settings.getVolumeSettings();
        volumeSettings.setEnable(
                !disableSounds.isSelected()
        );
        volumeSettings.setBackgroundMusicPercentage(
                backgroundVolumeSlider.getVolume()
        );
        volumeSettings.setEffectsPercentage(
                effectsVolumeSlider.getVolume()
        );
    }

    private void updateKeyBinding() {
        KeyboardSettings keyboardSettings = settings.getKeyboardSettings();
        keyboardSettings.setChopLeft(chopLeft.getKeyCode());
        keyboardSettings.setChopRight(chopRight.getKeyCode());
    }


    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAll(GameEvent event) {
        observers.forEach(o -> o.notify(event));
    }
}
