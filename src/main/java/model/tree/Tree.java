package model.tree;

import lombok.Getter;
import lombok.Setter;
import model.state.Position;

import java.util.concurrent.ThreadLocalRandom;

public class Tree {
    private static final int CAPACITY = 512;
    private final TreeBlock[] blocks = new TreeBlock[CAPACITY];
    private int currentIndex;
    @Getter
    @Setter
    private int visibleAmount = 5;

    public Tree() {
        createTreeBlocks();
        initializeTreeBlocks(0, CAPACITY);
    }

    public TreeBlock getVisibleBlockAt(int position) {
        if (position >= visibleAmount) {
            throw new IllegalArgumentException("Position cannot be greater than visible amount");
        }
        return blocks[(currentIndex + position) % CAPACITY];
    }

    public TreeBlock getCurrentTreeBlock() {
        return blocks[currentIndex];
    }

    public TreeBlock nextTreeBlock() {
        updateCurrentIndex();
        if (isUpdateRequired()) {
            updateTreeBlocks();
        }
        return getCurrentTreeBlock();
    }

    private void updateCurrentIndex() {
        currentIndex = ++currentIndex % CAPACITY;
    }

    private void updateTreeBlocks() {
        if (!isUpdateRequired()) {
            return;
        }
        switch (CAPACITY / (currentIndex + 1)) {
            case 2: {
                initializeTreeBlocks(0, CAPACITY >>> 1);
                break;
            }
            case 1: {
                initializeTreeBlocks(CAPACITY >>> 1, CAPACITY);
                break;
            }
        }
    }

    private boolean isUpdateRequired() {
        return (currentIndex + 1) % (CAPACITY >>> 1) == 0;
    }

    private void createTreeBlocks() {
        for (int i = 0; i < CAPACITY; i++) {
            blocks[i] = new TreeBlock();
        }
    }

    private void initializeTreeBlocks(int fromIndex, int toIndex) {
        for (int i = fromIndex; i < toIndex; i++) {
            int rand = ThreadLocalRandom.current().nextInt(-1, 2);
            initializeTreeBlock(rand, i);
        }
    }

    private void initializeTreeBlock(int state, int index) {
        switch (state) {
            case -1: {
                blocks[index].setChopped(false);
//                blocks[index].setBranch(true);
                blocks[index].setBranchPosition(Position.LEFT);
                break;
            }
            case 1: {
                blocks[index].setChopped(false);
//                blocks[index].setBranch(true);
                blocks[index].setBranchPosition(Position.RIGHT);
                break;
            }
            default: {
                blocks[index].setChopped(false);
//                blocks[index].setBranch(false);
                blocks[index].setBranchPosition(Position.NONE);
                break;
            }
        }
    }
}
