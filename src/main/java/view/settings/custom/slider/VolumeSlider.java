package view.settings.custom.slider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class VolumeSlider extends JComponent {
    private final JSlider volumeSlider;
    private final JLabel volumeLabel;
    private final String label;

    public VolumeSlider(int min, int max, int startVolume, String label) {
        this.label = label;

        setLayout(new BorderLayout(10, 10));

        volumeSlider = new JSlider(JSlider.HORIZONTAL, min, max, startVolume);
        volumeSlider.setOpaque(false);
        volumeSlider.addChangeListener(this::onSlide);

        volumeLabel = new JLabel(buildLabel(startVolume));
        volumeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(volumeSlider, BorderLayout.SOUTH);
        add(volumeLabel, BorderLayout.CENTER);
        setVisible(true);
        setFocusable(true);
    }

    private void onSlide(ChangeEvent event) {
        int volume = volumeSlider.getValue();
        volumeLabel.setText(buildLabel(volume));
    }

    private String buildLabel(int volume) {
        return String.format("%s %d", label, volume);
    }

    public int getVolume() {
        return volumeSlider.getValue();
    }
}

