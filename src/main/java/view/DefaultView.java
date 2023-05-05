package view;

import lombok.RequiredArgsConstructor;
import model.drawbuffer.DrawableBuffer;
import service.observer.GameObserver;

@RequiredArgsConstructor
public abstract class DefaultView implements GameObserver {
    protected final DrawableBuffer buffer;
}
