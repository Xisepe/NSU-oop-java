import controller.game.GameController;
import controller.player.KeyboardController;
import controller.player.LogicController;
import controller.settings.SettingsController;
import controller.sound.SoundController;
import model.asset.BackgroundViewData;
import model.asset.GameSoundData;
import model.asset.LumberjackViewData;
import model.asset.TreeViewData;
import model.draw.buffer.DrawableBuffer;
import model.player.Lumberjack;
import model.settings.Settings;
import model.state.score.Score;
import model.time.GameTime;
import model.tree.Tree;
import service.loader.settings.SettingsServiceImpl;
import service.loader.sound.SoundLoader;
import view.GameOverView;
import view.game.*;
import view.menu.MenuView;
import view.settings.SettingsView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game {
    //models
    private final GameTime gameTime = new GameTime();
    private final Settings settings = SettingsServiceImpl.getInstance().load("settings.json");
    private final Lumberjack player = new Lumberjack();
    private final Tree tree = new Tree();
    private final Score score = new Score();
    private final LumberjackViewData lumberjackViewData = new LumberjackViewData();
    private final TreeViewData treeViewData = new TreeViewData();
    private final BackgroundViewData backgroundViewData = new BackgroundViewData();
    private final DrawableBuffer buffer = new DrawableBuffer() {{
        setBuffer(new BufferedImage(
                settings.getVideoSettings().getCurrentScreenResolution().getWidth(),
                settings.getVideoSettings().getCurrentScreenResolution().getHeight(),
                BufferedImage.TYPE_INT_RGB
        ));
    }};
    private final GameSoundData gameSoundData = new GameSoundData(
            SoundLoader.getInstance().load("theme.wav"),
            SoundLoader.getInstance().load("hover.wav"),
            SoundLoader.getInstance().load("chop.wav"),
            SoundLoader.getInstance().load("game_over.wav")
    );
    //view
    private final LumberjackView lumberjackView = new LumberjackView(buffer, player, lumberjackViewData);
    private final TreeView treeView = new TreeView(buffer, tree, treeViewData);
    private final MenuView menuView = new MenuView(backgroundViewData);
    private final GameOverView gameOverView = new GameOverView(backgroundViewData, score);
    private final SettingsView settingsView = new SettingsView(settings);
    private final ScoreView scoreView = new ScoreView(score);
    private final TimerView timerView = new TimerView(gameTime);
    private final GameView gameView = new GameView(buffer, backgroundViewData){{
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        add(scoreView);
        add(timerView);
    }};
    private final JPanelHolder panelHolder = new JPanelHolder();
    //controllers
    private final KeyboardController keyboardController = new KeyboardController(settings.getKeyboardSettings());
    private final LogicController logicController = new LogicController(
            gameTime,
            keyboardController,
            player,
            tree,
            score
    );
    private final SettingsController settingsController = new SettingsController(settings, logicController);
    private final SoundController soundController = new SoundController(gameSoundData, settings.getVolumeSettings());
    private final GameController gameController = new GameController(
            gameTime, player, tree, score, settings, buffer,
            panelHolder, gameView, gameOverView, settingsView, menuView, scoreView, timerView, lumberjackView, treeView,
            lumberjackViewData, treeViewData, backgroundViewData, logicController, soundController, settingsController,
            keyboardController
    );

    private void start() {
        gameController.start();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
        EventQueue.invokeLater(() -> {
            game.panelHolder.setVisible(true);
        });
    }
}
