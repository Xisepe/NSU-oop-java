package view;

import lombok.RequiredArgsConstructor;
import service.observer.GameObserver;

import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public abstract class DefaultView implements GameObserver {
    protected BufferedImage buffer;
}
