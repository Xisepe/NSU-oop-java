package service.dependency.resolver;

import controller.game.GameController;
import controller.player.KeyboardController;
import controller.player.LogicController;
import controller.settings.SettingsController;
import controller.sound.SoundController;
import lombok.RequiredArgsConstructor;
import service.observer.GameObservable;
import view.GameOverView;
import view.game.*;
import view.menu.MenuView;
import view.settings.SettingsView;

import javax.swing.*;

@RequiredArgsConstructor
public class GameDependencyResolver implements DependencyResolver {
    private final GameController gameController;
    private final SoundController soundController;
    private final LogicController logicController;
    private final KeyboardController keyboardController;
    private final SettingsController settingsController;

    private final ScoreView scoreView;
    private final TimerView timerView;
    private final LumberjackView lumberjackView;
    private final TreeView treeView;
    private final JPanel menuView;
    private final JPanel settingsView;
    private final JPanel gameView;
    private final JPanel gameOverView;

    @Override
    public void resolveDependencies() {
        resolveLogicControllerDependencies();
        resolveMenuViewDependencies();
        resolveSettingsViewDependencies();
        resolveGameViewDependencies();
        resolveGameOverViewDependencies();
    }

    public void resolveGameOverViewDependencies() {
        connectGameControllerToGameObservable((GameOverView) gameOverView);
        connectSoundToGameObservable((GameOverView) gameOverView);
    }

    private void resolveLogicControllerDependencies() {
        connectViewsToLogicController();
        connectSoundToGameObservable(logicController);
        connectGameControllerToGameObservable(logicController);
    }

    private void resolveMenuViewDependencies() {
        connectGameControllerToGameObservable((MenuView) menuView);
        connectSoundToGameObservable((MenuView) menuView);
    }

    private void resolveSettingsViewDependencies() {
        ((SettingsView) settingsView).addObserver(settingsController);
    }

    private void resolveGameViewDependencies() {
        connectKeyboardControllerToGameView();
    }

    private void connectViewsToLogicController() {
        logicController.addObserver((GameView) gameView);
        logicController.addObserver(lumberjackView);
        logicController.addObserver(treeView);
        logicController.addObserver(scoreView);
        logicController.addObserver(timerView);
    }

    private void connectKeyboardControllerToGameView() {
        gameView.addKeyListener(keyboardController);
    }

    private void connectGameControllerToGameObservable(GameObservable observable) {
        observable.addObserver(gameController);
    }

    private void connectSoundToGameObservable(GameObservable observable) {
        observable.addObserver(soundController);
    }
}
