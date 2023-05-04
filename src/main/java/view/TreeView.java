package view;

import model.asset.TreeViewData;
import model.event.GameEvent;
import model.event.tree.DrawChoppedTreeEvent;
import model.event.tree.DrawAllTreeEvent;
import model.event.tree.TreeEvent;
import model.tree.Tree;
import model.tree.TreeBlock;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TreeView extends DefaultView {

    private final Tree tree;
    private final TreeViewData viewData;

    public TreeView(BufferedImage buffer, Tree tree, TreeViewData viewData) {
        super(buffer);
        this.tree = tree;
        this.viewData = viewData;
    }

    private void drawStamp(Graphics2D graphics2D) {
        graphics2D.drawImage(
                viewData.getStump(),
                viewData.getXOffsetStump(),
                viewData.getYOffsetStump(),
                null
        );
    }

    private void drawTreeBlock(Graphics2D graphics2D, TreeBlock treeBlock, int index) {
        BufferedImage treeBlockImage = getTreeBlockImage(treeBlock);
        graphics2D.drawImage(
                treeBlockImage,
                viewData.getXOffsetTree(),
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
                return viewData.getBranchLeft();
            case RIGHT:
                return viewData.getBranchRight();
            case NONE:
                return viewData.getTrunk();
        }
        return null;
    }

    @Override
    public void notify(GameEvent event) {
        if (!(event instanceof TreeEvent)) {
            return;
        }
        if (event instanceof DrawChoppedTreeEvent) {
            drawTreeFromTop((Graphics2D) buffer.getGraphics(), tree.getVisibleAmount() - 1);
        } else if (event instanceof DrawAllTreeEvent) {
            drawTreeFromTop((Graphics2D) buffer.getGraphics(), tree.getVisibleAmount());
        }
    }
}
