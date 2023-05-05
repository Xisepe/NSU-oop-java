package service.loader.viewdata;

import lombok.SneakyThrows;
import model.asset.TreeViewData;
import service.loader.ResourceLoader;
import service.loader.image.ImageLoader;

import java.util.Properties;

public class TreeViewDataLoader implements ResourceLoader<TreeViewData> {
    private static TreeViewDataLoader INSTANCE;
    private final ImageLoader imageLoader = ImageLoader.getInstance();

    private TreeViewDataLoader() {
    }

    public static TreeViewDataLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TreeViewDataLoader();
        }
        return INSTANCE;
    }

    @SneakyThrows
    @Override
    public TreeViewData load(String name) {
        TreeViewData treeViewData = new TreeViewData();
        treeViewData.setTrunk(imageLoader.load(name+"/trunk.png"));
        treeViewData.setStump(imageLoader.load(name+"/stump.png"));
        treeViewData.setBranchLeft(imageLoader.load(name+"/branch_left.png"));
        treeViewData.setBranchRight(imageLoader.load(name+"/branch_right.png"));
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("sprites/"+name+"/tree.properties"));
        treeViewData.setXOffsetTree(Integer.parseInt(properties.getProperty("xOffsetTree")));
        treeViewData.setYOffsetStump(Integer.parseInt(properties.getProperty("yOffsetStump")));
        treeViewData.setXOffsetTree(Integer.parseInt(properties.getProperty("xOffsetStump")));
        return treeViewData;
    }
}
