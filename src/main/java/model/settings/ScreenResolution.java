package model.settings;

import lombok.Data;

@Data

public class ScreenResolution {
    private int width;
    private int height;

    @Override
    public String toString() {
        return String.format("%dx%d", width, height);
    }
}
