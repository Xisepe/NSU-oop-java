package model.asset;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;

@Data
@AllArgsConstructor
public class LumberjackAssets {
    private BufferedImage chopLeft;
    private BufferedImage chopRight;
    private BufferedImage stayLeft;
    private BufferedImage stayRight;
    private int xOffsetChop;
    private int yOffsetChop;
    private int xOffsetStay;
    private int yOffsetStay;
}
