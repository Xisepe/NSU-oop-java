package view.settings.custom.dropmenu;

import model.settings.ScreenResolution;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class VideoDropMenu extends JComboBox<String> {
    public VideoDropMenu(ScreenResolution[] resolutions, int current) {
        super( Arrays
                .stream(resolutions)
                .map(e->String.format("%dx%d",e.getWidth(), e.getHeight()))
                .toArray(String[]::new)
        );
        setBackground(Color.BLACK);
        setForeground(Color.GREEN);
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(Color.BLACK);
                c.setForeground(Color.GREEN);
                return c;
            }
        });
        setSelectedIndex(current);
    }
}