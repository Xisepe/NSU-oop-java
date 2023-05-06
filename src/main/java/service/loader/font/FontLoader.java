package service.loader.font;

import lombok.SneakyThrows;
import service.loader.ResourceLoader;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FontLoader implements ResourceLoader<Font> {
    private static FontLoader INSTANCE;
    private Map<String, Font> fonts = new HashMap<String, Font>();

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
        fonts.putIfAbsent(name, Font.createFont(
                Font.TRUETYPE_FONT,
                this.getClass().getClassLoader().getResourceAsStream("font/" + name)
        ));
        return fonts.get(name);
    }
}
