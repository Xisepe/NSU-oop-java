package controller.game;

import controller.player.KeyboardController;
import controller.player.LogicController;
import controller.settings.SettingsController;
import controller.sound.SoundController;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import model.asset.BackgroundViewData;
import model.asset.LumberjackViewData;
import model.asset.TreeViewData;
import model.draw.buffer.DrawableBuffer;
import model.event.GameEvent;
import model.event.game.ExitFromGameEvent;
import model.event.game.GameControlsEvent;
import model.event.game.ResizeViewsGameEvent;
import model.event.game.changescreen.GameOverEvent;
import model.event.game.changescreen.MenuEvent;
import model.event.game.changescreen.OpenSettingsEvent;
import model.event.game.changescreen.StartGameEvent;
import model.event.player.ScoreUpdateEvent;
import model.player.Lumberjack;
import model.settings.Settings;
import model.state.Action;
import model.state.Position;
import model.state.score.Score;
import model.time.GameTime;
import model.tree.Tree;
import service.dependency.resolver.DependencyResolver;
import service.dependency.resolver.GameDependencyResolver;
import service.loader.font.FontLoader;
import service.loader.image.ImageLoader;
import service.loader.viewdata.LumberjackViewDataLoader;
import service.loader.viewdata.TreeViewDataLoader;
import service.observer.GameObserver;
import view.GameOverView;
import view.game.LumberjackView;
import view.game.ScoreView;
import view.game.TimerView;
import view.game.TreeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

@RequiredArgsConstructor
public class GameController implements ActionListener, GameObserver {
    //model
    private final GameTime gameTime;
    private final Lumberjack player;
    private final Tree tree;
    private final Score score;
    private final Settings settings;

    private static final int TICK_INTERVAL = 34;
    private final Timer gameLoop = new Timer(TICK_INTERVAL, this);

    private boolean gamePLaying;

    //view
    private final DrawableBuffer buffer;
    private final JFrame panelHolder;
    private final JPanel gameView;
    private final JPanel gameOverView;
    private final JPanel settingsView;
    private final JPanel menuView;

    private final ScoreView scoreView;
    private final TimerView timerView;
    private final LumberjackView lumberjackView;
    private final TreeView treeView;

    private final LumberjackViewData lumberjackViewData;
    private final TreeViewData treeViewData;
    private final BackgroundViewData backgroundViewData;

    //controller
    private final LogicController logicController;
    private final SoundController soundController;
    private final SettingsController settingsController;
    private final KeyboardController keyboardController;


    private void resolveDependencies() {
        DependencyResolver dependencyResolver = new GameDependencyResolver(
                this, soundController, logicController, keyboardController, settingsController,
                scoreView, timerView, lumberjackView, treeView, menuView, settingsView, gameView, gameOverView
        );
        dependencyResolver.resolveDependencies();
    }

    private void startGame() {
        gamePLaying = true;
        initializePlayer();
        initializeTree();
        initializeScore();
        initializeTime();
        resetKeyboardController();
        replacePanelOnHolder(gameView);
        updateGameLoop();
        startGameLoop();
    }

    private void gameOver() {
        stopGameLoop();
        openGameOverView();
    }

    private void openGameOverView() {
        gamePLaying = false;
        ((GameOverView) gameOverView).updateScoreLabel();
        replacePanelOnHolder(gameOverView);
    }

    private void openSettings() {
        gamePLaying = false;
        replacePanelOnHolder(settingsView);
    }

    private void openMenu() {
        gamePLaying = false;
        replacePanelOnHolder(menuView);
    }

    private void exitFromGame() {
        gamePLaying = false;
        System.exit(0);
    }

    private void replacePanelOnHolder(JPanel panel) {
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(panel);
        panelHolder.revalidate();
        panelHolder.repaint();
        panelHolder.pack();
    }

    private void startGameLoop() {
        gameLoop.start();
        gameTime.start();
    }

    private void stopGameLoop() {
        gameLoop.stop();
        gameTime.stop();
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

    private void initializeTime() {
        gameTime.drop();
        timerView.updateTimeLabel();
    }

    private void resetKeyboardController() {
        keyboardController.dropKeys();
    }

    private void updateGameLoop() {
        gameView.requestFocus();
        logicController.update();
        gameView.revalidate();
        gameView.repaint();
    }

    public void start() {
        resolveDependencies();
        resizeViews();
        replacePanelOnHolder(menuView);
        soundController.updateVolume();
        soundController.playBackground();
    }

    private void resizeViews() {
        int newWidth = settings.getVideoSettings().getCurrentScreenResolution().getWidth();
        int newHeight = settings.getVideoSettings().getCurrentScreenResolution().getHeight();
        Dimension newSize = new Dimension(newWidth, newHeight);
        reloadViewData();
        resizeBuffer(newWidth, newHeight);
        updateUIManager();
        resizeComponent(newSize, gameView);
        resizeComponent(newSize, settingsView);
        resizeComponent(newSize, panelHolder);
        resizeComponent(newSize, gameOverView);
    }

    @SneakyThrows
    private void updateUIManager() {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("font/font.properties"));
        updateFontSize(
                Integer.parseInt(properties.getProperty(settings.getVideoSettings().getCurrentScreenResolution() + ".menuView")),
                menuView
        );
        updateFontSize(
                Integer.parseInt(properties.getProperty(settings.getVideoSettings().getCurrentScreenResolution() + ".gameView")),
                gameView
        );
        updateFontSize(
                Integer.parseInt(properties.getProperty(settings.getVideoSettings().getCurrentScreenResolution() + ".settingsView")),
                settingsView
        );
        updateFontSize(
                Integer.parseInt(properties.getProperty(settings.getVideoSettings().getCurrentScreenResolution() + ".gameOverView")),
                gameOverView
        );
    }

    private void updateFontSize(float fontSize, Component component) {
        Font font = FontLoader.getInstance().load("arcadeclassic.regular.ttf").deriveFont(fontSize);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("TabbedPane.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("Tree.font", font);
        setFontForAllChildComponents(component, font);
    }

    private void setFontForAllChildComponents(Component component, Font font) {
        component.setFont(font);
        if (component instanceof Container) {
            Component[] children = ((Container) component).getComponents();
            for (Component child : children) {
                setFontForAllChildComponents(child, font);
            }
        }
    }

    private void reloadViewData() {
        String path = settings.getVideoSettings().getCurrentScreenResolution().toString();
        loadLumberjackViewData(path);
        loadTreeViewData(path);
        backgroundViewData.setBackground(ImageLoader.getInstance().load(path + "/background.png"));
    }

    private void loadLumberjackViewData(String path) {
        LumberjackViewData load = LumberjackViewDataLoader.getInstance().load(path);
        lumberjackViewData.setXOffset(load.getXOffset());
        lumberjackViewData.setYOffset(load.getYOffset());
        lumberjackViewData.setSwingRight(load.getSwingRight());
        lumberjackViewData.setSwingLeft(load.getSwingLeft());
        lumberjackViewData.setStandLeft(load.getStandLeft());
        lumberjackViewData.setStandRight(load.getStandRight());
        lumberjackViewData.setChopLeft(load.getChopLeft());
        lumberjackViewData.setChopRight(load.getChopRight());
    }

    private void loadTreeViewData(String path) {
        TreeViewData load = TreeViewDataLoader.getInstance().load(path);
        treeViewData.setXOffsetTree(load.getXOffsetTree());
        treeViewData.setYOffsetStump(load.getYOffsetStump());
        treeViewData.setXOffsetStump(load.getXOffsetStump());
        treeViewData.setTrunk(load.getTrunk());
        treeViewData.setStump(load.getStump());
        treeViewData.setBranchRight(load.getBranchRight());
        treeViewData.setBranchLeft(load.getBranchLeft());
    }

    private void resizeBuffer(int width, int height) {
        buffer.resize(width, height);
    }

    private void resizeComponent(Dimension newSize, Component panel) {
        panel.setPreferredSize(newSize);
        panel.invalidate();
        panel.revalidate();
        panel.repaint();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gamePLaying) {
            stopGameLoop();
            return;
        }
        updateGameLoop();
    }

    @Override
    public void notify(GameEvent event) {
        if (!(event instanceof GameControlsEvent)) {
            return;
        }
        if (event instanceof MenuEvent) {
            openMenu();
        } else if (event instanceof StartGameEvent) {
            startGame();
        } else if (event instanceof GameOverEvent) {
            gameOver();
        } else if (event instanceof OpenSettingsEvent) {
            openSettings();
        } else if (event instanceof ExitFromGameEvent) {
            exitFromGame();
        } else if (event instanceof ResizeViewsGameEvent) {
            resizeViews();
        }
    }
}
