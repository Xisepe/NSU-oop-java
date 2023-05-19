package model.settings;

import lombok.Data;

import java.awt.event.KeyEvent;

@Data
public class KeyboardSettings {
    private int chopLeft = KeyEvent.VK_A;
    private int chopRight = KeyEvent.VK_D;
}
