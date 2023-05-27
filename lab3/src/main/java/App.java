import controller.ViewController;
import factory.Factory;
import ui.FactoryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class App {
    private static final int UPDATE_FREQUENCY_MS = 20;
    private final Factory factory = new Factory();
    private final FactoryView factoryView = new FactoryView(factory);
    private final ViewController controller = new ViewController(factoryView);
    private final Timer timer = new Timer(UPDATE_FREQUENCY_MS, controller);

    public void start() {
        timer.start();
        factory.start();
    }
    public void stop() {
        timer.stop();
        factory.stop();
    }
    public static void main(String[] args) {
        App app = new App();
        app.start();
        EventQueue.invokeLater(()->{
            JFrame frame = new JFrame();
            frame.setPreferredSize(new Dimension(800, 600));
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    app.stop();
                    System.exit(0);
                }
            });
            frame.add(app.factoryView);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
