package service.loader.viewdata;

import lombok.SneakyThrows;
import model.asset.LumberjackViewData;
import service.loader.ResourceLoader;
import service.loader.image.ImageLoader;

import java.util.Properties;

public class LumberjackViewDataLoader implements ResourceLoader<LumberjackViewData> {
    private static LumberjackViewDataLoader INSTANCE;
    private final ImageLoader imageLoader = ImageLoader.getInstance();

    public static LumberjackViewDataLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LumberjackViewDataLoader();
        }
        return INSTANCE;
    }

    private LumberjackViewDataLoader() {}

    @SneakyThrows
    @Override
    public LumberjackViewData load(String name) {
        LumberjackViewData lumberjackViewData = new LumberjackViewData();
        lumberjackViewData.setChopLeft(imageLoader.load(name+"/chop_left.png"));
        lumberjackViewData.setChopRight(imageLoader.load(name+"/chop_right.png"));
        lumberjackViewData.setStandLeft(imageLoader.load(name+"/stand_left.png"));
        lumberjackViewData.setStandRight(imageLoader.load(name+"/stand_right.png"));
        lumberjackViewData.setSwingLeft(imageLoader.load(name+"/swing_left.png"));
        lumberjackViewData.setSwingRight(imageLoader.load(name+"/swing_left.png"));
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("sprites/"+name+"/player.properties"));
        lumberjackViewData.setXOffset(Integer.parseInt(properties.getProperty("xOffset")));
        lumberjackViewData.setYOffset(Integer.parseInt(properties.getProperty("yOffset")));
        return lumberjackViewData;
    }
}
