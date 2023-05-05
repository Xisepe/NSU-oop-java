package service.loader.image;

import lombok.SneakyThrows;
import service.loader.ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class ImageLoader implements ResourceLoader<BufferedImage> {
    private static ImageLoader INSTANCE;
    private static final String PATH = "sprites/";
    private ImageLoader() {

    }

    public static synchronized ImageLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageLoader();
        }
        return INSTANCE;
    }

    @SneakyThrows
    @Override
    public BufferedImage load(String name) {
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH + name);
        return ImageIO.read(resource);
    }
}
