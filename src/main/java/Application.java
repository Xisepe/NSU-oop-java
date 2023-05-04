import model.asset.LumberjackViewData;
import model.player.Lumberjack;
import model.tree.Tree;
import service.loader.ImageLoader;

import javax.swing.*;

public class Application {
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
    }
}
