package controller.game;

import controller.Controller;
import controller.settings.SettingsController;
import lombok.RequiredArgsConstructor;
import model.asset.BackgroundViewData;
import model.asset.LumberjackViewData;
import model.asset.TreeViewData;
import model.drawbuffer.DrawableBuffer;
import model.event.GameEvent;
import model.event.game.ExitFromGameEvent;
import model.event.game.GameControlsEvent;
import model.event.game.ResizeViewsGameEvent;
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
import java.awt.*;
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
    private final JPanel settingsView;

    private final LumberjackViewData lumberjackViewData;
    private final TreeViewData treeViewData;
    private final BackgroundViewData backgroundViewData;

    //controller
    private final Controller logicController;


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
        replacePanelOnHolder(settingsView);
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

    private void resizeViews() {
        int newWidth = settings.getVideoSettings().getCurrentScreenResolution().getWidth();
        int newHeight = settings.getVideoSettings().getCurrentScreenResolution().getHeight();


        resizeBuffer(newWidth, newHeight);
        resizeGameView(newWidth, newHeight);
        resizeSettingsView(newWidth, newHeight);
        resizePanelHolder(newWidth, newHeight);

    }

    private void reloadViewData() {

    }

    private void resizeBuffer(int width, int height) {
        buffer.resize(width, height);
    }

    private void resizeGameView(int width, int height) {
        gameView.setPreferredSize(new Dimension(width, height));
        gameView.repaint();
    }

    private void resizeSettingsView(int width, int height) {
        settingsView.setPreferredSize(new Dimension(width, height));
        settingsView.repaint();
    }

    private void resizePanelHolder(int width, int height) {
        panelHolder.setPreferredSize(new Dimension(width, height));
        panelHolder.revalidate();
        panelHolder.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //todo game over
    @Override
    public void notify(GameEvent event) {
        if (!(event instanceof GameControlsEvent)) {
            return;
        }
        if (event instanceof StartGameEvent) {
            startGame();
        } else if (event instanceof OpenSettingsEvent) {
            openSettings();
        } else if (event instanceof ExitFromGameEvent) {
            exitFromGame();
        } else if (event instanceof ResizeViewsGameEvent) {

        }
    }
}
