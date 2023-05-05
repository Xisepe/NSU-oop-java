import controller.Controller;
import model.asset.LumberjackViewData;
import model.event.GameEvent;
import model.player.Lumberjack;
import model.settings.ScreenResolution;
import model.tree.Tree;
import service.loader.ImageLoader;
import service.observer.GameObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Application extends JFrame implements ActionListener, GameObserver {

    private static final int TICK_INTERVAL = 17;
    private final Timer timer = new Timer(TICK_INTERVAL, this);
    private JPanel currentPanel;


    public Application(ScreenResolution resolution) throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(
                resolution.getWidth(),
                resolution.getHeight()
        ));
        setFocusable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(540,888);
        Lumberjack lumberjack = new Lumberjack();
        Tree tree = new Tree();
        LumberjackViewData playerAssets = new LumberjackViewData();
        playerAssets.setChopLeft(ImageLoader.getInstance().load("540x888/man_chop_left.png"));
        playerAssets.setChopRight(ImageLoader.getInstance().load("540x888/man_chop_right.png"));
        playerAssets.setStandLeft(ImageLoader.getInstance().load("540x888/man_stay_left.png"));
        playerAssets.setStandRight(ImageLoader.getInstance().load("540x888/man_stay_right.png"));
        EventQueue.invokeLater(()-> {

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.update();
    }

    @Override
    public void notify(GameEvent event) {

    }
}
