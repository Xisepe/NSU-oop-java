package view.components.mouseadapter;

import lombok.RequiredArgsConstructor;
import model.event.sound.effects.PlayHoverEffectSoundEvent;
import service.observer.GameObservable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@RequiredArgsConstructor
public class FireSoundOnHoverMouseAdapter extends MouseAdapter {
    private final GameObservable observable;

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        observable.notifyAll(new PlayHoverEffectSoundEvent());
    }
}
