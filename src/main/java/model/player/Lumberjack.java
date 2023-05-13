package model.player;

import lombok.Data;
import model.state.Action;
import model.state.Position;
import model.tree.Tree;

@Data
public class Lumberjack {
    private Position playerPosition = Position.LEFT;
    private boolean dead;
    private Action action = Action.STAND;

    public void chop(Tree tree) {
        tree.getCurrentTreeBlock().setChopped(true);
        if (playerPosition.equals(tree.getCurrentTreeBlock().getBranchPosition())) {
            dead = true;
        }
        tree.increment();
    }

    public void moveLeft() {
        if (playerPosition == Position.RIGHT) {
            playerPosition = Position.LEFT;
        }
    }

    public void moveRight() {
        if (playerPosition == Position.LEFT) {
            playerPosition = Position.RIGHT;
        }
    }
}
