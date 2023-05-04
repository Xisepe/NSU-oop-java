package view;

import model.asset.TreeAssets;
import model.event.GameEvent;
import model.event.tree.DrawChoppedTreeEvent;
import model.event.tree.DrawTreeEvent;
import model.tree.Tree;
import model.tree.TreeBlock;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TreeView extends DefaultView {

    private final Tree tree;
    private final TreeAssets treeAssets;

    public TreeView(BufferedImage buffer, Tree tree, TreeAssets treeAssets) {
        super(buffer);
        this.tree = tree;
        this.treeAssets = treeAssets;
    }

    private void drawStamp(Graphics2D graphics2D) {
        graphics2D.drawImage(
                treeAssets.getStump(),
                treeAssets.getXOffsetStump(),
                treeAssets.getYOffsetStump(),
                null
        );
    }

    private void drawTreeBlock(Graphics2D graphics2D, TreeBlock treeBlock, int index) {
        BufferedImage treeBlockImage = getTreeBlockImage(treeBlock);
        graphics2D.drawImage(
                treeBlockImage,
                treeAssets.getXOffsetTree(),
                treeBlockImage.getHeight() * index,
                null
        );
    }

    private void drawTreeFromTop(Graphics2D graphics2D, int number) {
        drawStamp(graphics2D);
        for (int i = 0; i < number; i++) {
            drawTreeBlock(
                    graphics2D,
                    tree.getVisibleBlockAt(tree.getVisibleAmount() - i),
                    i
                    );
        }
    }

    private BufferedImage getTreeBlockImage(TreeBlock treeBlock) {
        switch (treeBlock.getBranchPosition()) {
            case LEFT:
                return treeAssets.getBranchLeft();
            case RIGHT:
                return treeAssets.getBranchRight();
            case NONE:
                return treeAssets.getTrunk();
        }
        return null;
    }

    @Override
    public void notify(GameEvent event) {
        if (event instanceof DrawChoppedTreeEvent) {
            drawTreeFromTop((Graphics2D) buffer.getGraphics(), tree.getVisibleAmount() - 1);
        } else if (event instanceof DrawTreeEvent) {
            drawTreeFromTop((Graphics2D) buffer.getGraphics(), tree.getVisibleAmount());
        }
    }
}
