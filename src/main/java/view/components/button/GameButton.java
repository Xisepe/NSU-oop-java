package view.components.button;

import service.observer.GameObservable;
import view.components.mouseadapter.FireSoundOnHoverMouseAdapter;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class GameButton extends JButton implements ActionListener {
    protected final GameObservable observable;

    public GameButton(String text, GameObservable observable) {
        super(text);
        this.observable = observable;
        addActionListener(this);
        addMouseListener(new FireSoundOnHoverMouseAdapter(observable));
    }

}
