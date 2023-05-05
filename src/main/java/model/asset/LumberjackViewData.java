package model.asset;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class LumberjackViewData {
    private BufferedImage swingLeft;
    private BufferedImage swingRight;
    private BufferedImage chopLeft;
    private BufferedImage chopRight;
    private BufferedImage standLeft;
    private BufferedImage standRight;
    private int xOffset;
    private int yOffset;
}
