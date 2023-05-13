package model.asset;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class TreeViewData {
    private BufferedImage branchLeft;
    private BufferedImage branchRight;
    private BufferedImage trunk;
    private BufferedImage stump;
    private int xOffsetTree;
    private int xOffsetStump;
    private int yOffsetStump;

}
