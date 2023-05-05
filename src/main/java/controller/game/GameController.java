package controller.game;

import lombok.RequiredArgsConstructor;
import model.event.GameEvent;
import model.player.Lumberjack;
import model.state.Action;
import model.state.Position;
import model.tree.Tree;
import service.observer.GameObserver;

@RequiredArgsConstructor
public class GameController implements GameObserver {
    private final Lumberjack player;
    private final Tree tree;

    private void initializePlayer() {
        player.setPlayerPosition(Position.LEFT);
        player.setAction(Action.STAND);
        player.setDead(false);
    }

    private void initializeTree() {
        tree.initializeTreeBlocks();
    }

    @Override
    public void notify(GameEvent event) {

    }
}
