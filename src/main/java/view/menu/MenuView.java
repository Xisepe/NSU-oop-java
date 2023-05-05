package view.menu;

import model.asset.BackgroundViewData;
import model.event.GameEvent;
import service.observer.GameObservable;
import service.observer.GameObserver;
import view.components.button.ExitFromGameButton;
import view.components.button.OpenSettingsButton;
import view.components.button.StartGameButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//todo: implement ObservableView/ObserverView


public class MenuView extends JPanel implements GameObservable {
    private final java.util.List<GameObserver> observers = new ArrayList<>();
    private final BackgroundViewData backgroundViewData;

    public MenuView(BackgroundViewData backgroundViewData) {
        this.backgroundViewData = backgroundViewData;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = initializeGridBagConstraints();
        add(new StartGameButton(this), gbc);
        add(new OpenSettingsButton(this), gbc);
        add(new ExitFromGameButton(this), gbc);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundViewData.getBackground(), 0, 0, null);
    }

    private GridBagConstraints initializeGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 0); // no padding
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
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
