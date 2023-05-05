package model.drawbuffer;

import lombok.Data;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

@Data
public class DrawableBuffer {
    private BufferedImage buffer;
    public void resize(int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, buffer.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        AffineTransform transform = AffineTransform.getScaleInstance(
                (double) width / buffer.getWidth(),
                (double) height / buffer.getHeight()
        );
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(buffer, transform, null);
        graphics2D.dispose();
        buffer = scaledImage;
    }
}
