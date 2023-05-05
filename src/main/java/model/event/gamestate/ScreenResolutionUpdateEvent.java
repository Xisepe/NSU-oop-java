package model.event.gamestate;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import model.event.GameEvent;
import model.settings.ScreenResolution;

@Data
public class ScreenResolutionUpdateEvent implements GameEvent {
    private final ScreenResolution resolution;
}
