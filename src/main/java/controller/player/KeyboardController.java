package controller.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.settings.KeyboardSettings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@RequiredArgsConstructor
public class KeyboardController implements KeyListener {
    private final KeyboardSettings settings;
    @Getter
    private boolean chopLeft;
    @Getter
    private boolean chopRight;


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == settings.getChopRight()) {
            chopRight = true;
        } else if (e.getKeyCode() == settings.getChopLeft()) {
            chopLeft = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == settings.getChopRight()) {
            chopRight = false;
        } else if (e.getKeyCode() == settings.getChopLeft()) {
            chopLeft = false;
        }
    }
}
