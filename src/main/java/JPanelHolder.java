import model.event.GameEvent;
import service.observer.GameObserver;

import javax.swing.*;
import java.awt.*;


public class JPanelHolder extends JFrame implements GameObserver {

    public JPanelHolder() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
    }

    @Override
    public void notify(GameEvent event) {

    }
}
