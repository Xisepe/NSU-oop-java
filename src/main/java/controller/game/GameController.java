package controller.game;

import controller.Controller;
import controller.settings.SettingsController;
import lombok.RequiredArgsConstructor;
import model.drawbuffer.DrawableBuffer;
import model.event.GameEvent;
import model.event.game.ExitFromGameEvent;
import model.event.game.GameControlsEvent;
import model.event.game.changescreen.OpenSettingsEvent;
import model.event.game.changescreen.StartGameEvent;
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

//todo: implement ObservableController/ObserverController

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
    private DrawableBuffer buffer;
    private final JFrame panelHolder;
    private final JPanel gameView;

    //controller
    private final Controller logicController;
    private final SettingsController settingsController;


    private void startGame() {
        initializePlayer();
        initializeTree();
        initializeScore();
        replacePanelOnHolder(gameView);
        updateGameLoop();
        startGameLoop();
    }

    private void gameOver() {
        stopGameLoop();
    }

    private void openSettings() {
//        replacePanelOnHolder();
    }

    private void exitFromGame() {
        System.exit(0);
    }

    private void replacePanelOnHolder(JPanel panel) {
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(panel);
        panelHolder.revalidate();
        panelHolder.repaint();
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

    private void updateGameLoop() {
        logicController.update();
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
        } else if (event instanceof OpenSettingsEvent) {

        } else if (event instanceof ExitFromGameEvent) {
            exitFromGame();
        }
    }
}
