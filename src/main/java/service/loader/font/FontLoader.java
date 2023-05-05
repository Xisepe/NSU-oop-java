package service.loader.font;

import lombok.SneakyThrows;
import service.loader.ResourceLoader;

import java.awt.*;

public class FontLoader implements ResourceLoader<Font> {
    private static FontLoader INSTANCE;

    private FontLoader() {}

    public static FontLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FontLoader();
        }
        return INSTANCE;
    }

    @SneakyThrows
    @Override
    public Font load(String name) {
        return Font.createFont(
                Font.TRUETYPE_FONT,
                this.getClass().getClassLoader().getResourceAsStream("fonts/" + name)
                );
    }
}
