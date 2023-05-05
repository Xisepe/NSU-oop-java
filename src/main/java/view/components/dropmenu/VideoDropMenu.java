package view.components.dropmenu;

import model.settings.ScreenResolution;
import service.observer.GameObservable;
import view.components.mouseadapter.FireSoundOnHoverMouseAdapter;

import javax.swing.*;
import java.util.Arrays;

public class VideoDropMenu extends JComboBox<String> {
    public VideoDropMenu(ScreenResolution[] resolutions, int current, GameObservable observable) {
        super( Arrays
                .stream(resolutions)
                .map(e->String.format("%dx%d",e.getWidth(), e.getHeight()))
                .toArray(String[]::new)
        );
        setSelectedIndex(current);
        addMouseListener(new FireSoundOnHoverMouseAdapter(observable));
    }
}