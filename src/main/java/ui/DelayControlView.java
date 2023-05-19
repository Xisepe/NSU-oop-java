package ui;

import factory.delay.Delay;

import javax.swing.*;
import java.awt.*;

public class DelayControlView extends JPanel {
    private final Delay delay;
    private final JLabel delayLabel;
    private final JSlider delaySlider;

    public DelayControlView(Delay delay, String name) {
        this.delay = delay;
        this.delayLabel = new JLabel(delay.getValue().toString() + " ms");
        this.delaySlider = new JSlider(delay.getMin(), delay.getMax(), delay.getValue());
        this.delaySlider.addChangeListener((e) -> {
            delay.setValue(delaySlider.getValue());
            delayLabel.setText(delay.getValue() + " ms");
        });
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(new JLabel(name));
        add(delayLabel);
        add(delaySlider);
    }

    public static void main(String[] args) {
        Delay d = new Delay();
        d.setValue(100);
        d.setMax(10000);
        DelayControlView dc = new DelayControlView(d, "Test");
        JFrame f = new JFrame();
        f.setPreferredSize(new Dimension(800, 600));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(dc);
        f.pack();
        f.setVisible(true);

    }
}
