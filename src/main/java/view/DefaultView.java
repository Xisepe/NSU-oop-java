package view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import service.observer.GameObserver;

import java.awt.image.BufferedImage;

@AllArgsConstructor
public abstract class DefaultView implements GameObserver {
    @Getter
    @Setter
    protected BufferedImage buffer;
}
