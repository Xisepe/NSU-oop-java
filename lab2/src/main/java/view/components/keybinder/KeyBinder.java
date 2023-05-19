package view.components.keybinder;

import service.observer.GameObservable;
import view.components.mouseadapter.FireSoundOnHoverMouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBinder extends JComponent implements KeyListener {
    private final JLabel actionLabel;
    private final JTextField keyField;

    private int keyCode;

    public KeyBinder(String action, String start, GameObservable observable) {
        addMouseListener(new FireSoundOnHoverMouseAdapter(observable));
        actionLabel = new JLabel(action);
        keyField = initializeKeyField(start);
        initializeLayout();
        setVisible(true);
        setFocusable(true);
    }

    private void initializeLayout() {
        setLayout(new BorderLayout());
        add(actionLabel, BorderLayout.WEST);
        add(keyField, BorderLayout.EAST);
    }

    private JTextField initializeKeyField(String start) {
        final JTextField keyField;
        keyField = new JTextField(KeyEvent.getKeyText(keyCode), 5);
        keyField.setEditable(false);
        keyField.setText(start);
        keyField.addKeyListener(this);
        return keyField;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
        keyField.setText(KeyEvent.getKeyText(keyCode));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setKeyCode(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}