package view;

import lombok.AllArgsConstructor;
import service.observer.GameObserver;

import java.awt.image.BufferedImage;

@AllArgsConstructor
public abstract class DefaultView implements GameObserver {
    protected BufferedImage buffer;
}
