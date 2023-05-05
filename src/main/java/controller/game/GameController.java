package controller.game;

import controller.Controller;
import lombok.RequiredArgsConstructor;
import model.event.GameEvent;
import model.event.game.GameControlsEvent;
import model.event.game.StartGameEvent;
import model.player.Lumberjack;
import model.settings.Settings;
import model.state.Action;
import model.state.Position;
import model.state.score.Score;
import model.tree.Tree;
import service.observer.GameObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class GameController implements ActionListener, GameObserver {
    //model
    private final Lumberjack player;
    private final Tree tree;
    private final Score score;
    private final Settings settings;

    private static final int TICK_INTERVAL = 17;
    private final Timer gameLoop = new Timer(TICK_INTERVAL, this);

    //view


    //controller
    private final Controller logicController;


    private void startGame() {
        startGameLoop();
        initializePlayer();
        initializeTree();
        initializeScore();
    }

    private void gameOver() {
        stopGameLoop();
    }

    private void startGameLoop() {
        gameLoop.start();
    }

    private void stopGameLoop() {
        gameLoop.stop();
    }

    private void initializePlayer() {
        player.setPlayerPosition(Position.LEFT);
        player.setAction(Action.STAND);
        player.setDead(false);
    }

    private void initializeTree() {
        tree.initializeTreeBlocks();
    }

    private void initializeScore() {
        score.setValue(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void notify(GameEvent event) {
        if (!(event instanceof GameControlsEvent)) {
            return;
        }
        if (event instanceof StartGameEvent) {
            startGame();
        }
    }
}
