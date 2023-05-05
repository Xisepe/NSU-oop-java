import model.event.GameEvent;
import service.observer.GameObserver;

import javax.swing.*;
import java.awt.*;


public class JPanelHolder extends JFrame{

    public JPanelHolder() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
    }
}
